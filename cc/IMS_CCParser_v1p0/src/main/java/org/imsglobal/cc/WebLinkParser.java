package org.imsglobal.cc;

/**********************************************************************************
 * $URL: http://ims-dev.googlecode.com/svn/trunk/cc/IMS_CCParser_v1p0/src/main/java/org/imsglobal/cc/WebLinkParser.java $
 * $Id: WebLinkParser.java 227 2011-01-08 18:26:55Z drchuck $
 **********************************************************************************
 *
 * Copyright (c) 2010 IMS GLobal Learning Consortium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. 
 *
 **********************************************************************************/

import java.io.IOException;

import org.jdom.Element;
import org.jdom.Namespace;

public class WebLinkParser extends AbstractParser implements ContentParser {

  private static final Namespace CC_NS = Namespace.getNamespace("ims", "http://www.imsglobal.org/xsd/imscc/imscp_v1p1");
  
  private static final String FILE="file";
  private static final String HREF="href";
  private static final String URL="url";
  private static final String TITLE="title";
  private static final String TARGET="target";
  private static final String WINDOW_FEATURES="windowFeatures";
  
  public void 
  parseContent(DefaultHandler the_handler,
               CartridgeLoader the_cartridge, 
               Element the_resource,
               boolean isProtected) throws ParseException {
    try {
      //ok, so we're looking at a web link here...
      Element link = getXML(the_cartridge, ((Element)the_resource.getChildren(FILE, CC_NS).get(0)).getAttributeValue(HREF));
      the_handler.startWebLink(link.getChildText(TITLE),
                               link.getChild(URL).getAttributeValue(HREF),
                               link.getChild(URL).getAttributeValue(TARGET),
                               link.getChild(URL).getAttributeValue(WINDOW_FEATURES),
                               isProtected);
      the_handler.setWebLinkXml(link);                         
      the_handler.endWebLink();
    } catch (IOException e) {
      throw new ParseException(e);
    }
  }
}
