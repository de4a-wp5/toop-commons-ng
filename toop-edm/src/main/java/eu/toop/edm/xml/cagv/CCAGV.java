/**
 * This work is protected under copyrights held by the members of the
 * TOOP Project Consortium as indicated at
 * http://wiki.ds.unipi.gr/display/TOOP/Contributors
 * (c) 2018-2021. All rights reserved.
 *
 * This work is dual licensed under Apache License, Version 2.0
 * and the EUPL 1.2.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL
 * (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *         https://joinup.ec.europa.eu/software/page/eupl
 */
package eu.toop.edm.xml.cagv;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.xsds.ccts.cct.schemamodule.CCCTS;
import com.helger.xsds.xlink.CXLink;
import com.helger.xsds.xml.CXML_XSD;

import eu.toop.edm.xml.cv.CCV;

/**
 * Core Agent Vocabulary (CAGV) constants
 *
 * @author Philip Helger
 */
public final class CCAGV
{
  @Nonnull
  private static final ClassLoader _getCL ()
  {
    return CCAGV.class.getClassLoader ();
  }

  public static final List <ClassPathResource> XSDS;
  static
  {
    final ICommonsList <ClassPathResource> aList = new CommonsArrayList <> ();
    aList.addAll (CCCTS.getXSDResource (),
                  CXML_XSD.getXSDResource (),
                  CXLink.getXSDResource (),
                  new ClassPathResource ("schemas/owl.xsd", _getCL ()),
                  new ClassPathResource ("schemas/skos.xsd", _getCL ()),
                  new ClassPathResource ("schemas/locn.xsd", _getCL ()),
                  new ClassPathResource ("schemas/foaf.xsd", _getCL ()),
                  new ClassPathResource ("schemas/org.xsd", _getCL ()),
                  new ClassPathResource ("schemas/rdf.xsd", _getCL ()),
                  new ClassPathResource ("schemas/dcterms.xsd", _getCL ()),
                  new ClassPathResource ("schemas/regorg.xsd", _getCL ()),
                  new ClassPathResource ("schemas/CV-DataTypes.xsd", _getCL ()),
                  new ClassPathResource ("schemas/CV-CommonBasicComponents.xsd", _getCL ()),
                  new ClassPathResource ("schemas/CV-CommonAggregateComponents.xsd", _getCL ()),
                  new ClassPathResource ("schemas/CV-Agent.xsd", _getCL ()));
    // Add W3 CoreVocabularies
    aList.addAll (CCV.XSDS);
    XSDS = aList.getAsUnmodifiable ();
  }

  private CCAGV ()
  {}
}
