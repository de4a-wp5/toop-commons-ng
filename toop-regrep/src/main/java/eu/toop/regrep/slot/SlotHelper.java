/**
 * Copyright (C) 2018-2020 toop.eu
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
 */
package eu.toop.regrep.slot;

import java.math.BigInteger;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Element;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.text.IMultilingualText;

import eu.toop.regrep.helper.VocabularyTerm;
import eu.toop.regrep.rim.AnyValueType;
import eu.toop.regrep.rim.BooleanValueType;
import eu.toop.regrep.rim.CollectionValueType;
import eu.toop.regrep.rim.DateTimeValueType;
import eu.toop.regrep.rim.EntryType;
import eu.toop.regrep.rim.FloatValueType;
import eu.toop.regrep.rim.IntegerValueType;
import eu.toop.regrep.rim.InternationalStringType;
import eu.toop.regrep.rim.InternationalStringValueType;
import eu.toop.regrep.rim.LocalizedStringType;
import eu.toop.regrep.rim.MapType;
import eu.toop.regrep.rim.MapValueType;
import eu.toop.regrep.rim.SlotType;
import eu.toop.regrep.rim.SlotValueType;
import eu.toop.regrep.rim.StringValueType;
import eu.toop.regrep.rim.ValueType;
import eu.toop.regrep.rim.VocabularyTermType;
import eu.toop.regrep.rim.VocabularyTermValueType;

/**
 * Helper class to simplify the creation of RegRep data constructs.
 *
 * @author Philip Helger
 */
@Immutable
public final class SlotHelper
{
  private SlotHelper ()
  {}

  @Nonnull
  public static LocalizedStringType createLocalizedString (@Nonnull final Locale aLocale, @Nonnull final String sText)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    ValueEnforcer.notNull (sText, "Text");
    return createLocalizedString (aLocale.getLanguage (), sText);
  }

  @Nonnull
  public static LocalizedStringType createLocalizedString (@Nonnull @Nonempty final String sLanguage, @Nonnull final String sText)
  {
    ValueEnforcer.notEmpty (sLanguage, "Language");
    ValueEnforcer.notNull (sText, "Text");
    final LocalizedStringType ret = new LocalizedStringType ();
    ret.setLang (sLanguage);
    ret.setValue (sText);
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final Map <String, String> aMap)
  {
    ValueEnforcer.noNullValue (aMap, "Map");

    final InternationalStringType ret = new InternationalStringType ();
    if (aMap != null)
      for (final Map.Entry <String, String> aEntry : aMap.entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final IMultilingualText aMLT)
  {
    final InternationalStringType ret = new InternationalStringType ();
    if (aMLT != null)
      for (final Map.Entry <Locale, String> aEntry : aMLT.texts ().entrySet ())
        ret.addLocalizedString (createLocalizedString (aEntry.getKey (), aEntry.getValue ()));
    return ret;
  }

  @Nonnull
  public static InternationalStringType createInternationalStringType (@Nullable final LocalizedStringType... aArray)
  {
    ValueEnforcer.noNullValue (aArray, "Array");

    final InternationalStringType ret = new InternationalStringType ();
    if (aArray != null)
      for (final LocalizedStringType aItem : aArray)
        ret.addLocalizedString (aItem);
    return ret;
  }

  @Nonnull
  public static MapType createMap (@Nullable final Map <? extends ValueType, ? extends ValueType> aMap)
  {
    ValueEnforcer.notNull (aMap, "Value");
    final MapType ret = new MapType ();
    if (aMap != null)
      for (final Map.Entry <? extends ValueType, ? extends ValueType> aEntry : aMap.entrySet ())
      {
        final EntryType aRegRepEntry = new EntryType ();
        aRegRepEntry.setEntryKey (aEntry.getKey ());
        aRegRepEntry.setEntryValue (aEntry.getValue ());
        ret.addEntry (aRegRepEntry);
      }
    return ret;
  }

  @Nonnull
  public static VocabularyTermType createVocabularyTerm (@Nonnull final VocabularyTerm aTerm)
  {
    ValueEnforcer.notNull (aTerm, "Term");
    return createVocabularyTerm (aTerm.getVocabulary (), aTerm.getTerm ());
  }

  @Nonnull
  public static VocabularyTermType createVocabularyTerm (@Nonnull final String sVocabulary, @Nonnull final String sTerm)
  {
    ValueEnforcer.notNull (sVocabulary, "Vocabulary");
    ValueEnforcer.notNull (sTerm, "Term");
    final VocabularyTermType ret = new VocabularyTermType ();
    ret.setVocabulary (sVocabulary);
    ret.setTerm (sTerm);
    return ret;
  }

  @Nonnull
  public static AnyValueType createSlotValue (@Nonnull final Element x)
  {
    ValueEnforcer.notNull (x, "Value");
    final AnyValueType ret = new AnyValueType ();
    ret.setAny (x);
    return ret;
  }

  @Nonnull
  public static BooleanValueType createSlotValue (final boolean x)
  {
    return new BooleanValueType (Boolean.valueOf (x));
  }

  @Nonnull
  public static CollectionValueType createSlotValue (@Nullable final ValueType... x)
  {
    return createSlotValue ((ERegRepCollectionType) null, x);
  }

  @Nonnull
  public static CollectionValueType createSlotValue (@Nullable final ERegRepCollectionType eType, @Nullable final ValueType... x)
  {
    ValueEnforcer.noNullValue (x, "Value");
    final CollectionValueType ret = new CollectionValueType ();
    if (eType != null)
      ret.setCollectionType (eType.getID ());
    if (x != null)
      for (final ValueType aItem : x)
        ret.addElement (aItem);
    return ret;
  }

  @Nonnull
  public static CollectionValueType createSlotValue (@Nullable final ERegRepCollectionType eType,
                                                     @Nullable final Iterable <? extends ValueType> x)
  {
    ValueEnforcer.noNullValue (x, "Value");
    final CollectionValueType ret = new CollectionValueType ();
    if (eType != null)
      ret.setCollectionType (eType.getID ());
    if (x != null)
      for (final ValueType aItem : x)
        ret.addElement (aItem);
    return ret;
  }

  @Nonnull
  public static DateTimeValueType createSlotValue (@Nonnull final XMLGregorianCalendar x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new DateTimeValueType (x);
  }

  @Nonnull
  public static FloatValueType createSlotValue (final float x)
  {
    return new FloatValueType (Float.valueOf (x));
  }

  @Nonnull
  public static IntegerValueType createSlotValue (@Nonnull final BigInteger x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new IntegerValueType (x);
  }

  @Nonnull
  public static InternationalStringValueType createSlotValue (@Nonnull final InternationalStringType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new InternationalStringValueType (x);
  }

  @Nonnull
  public static MapValueType createSlotValue (@Nonnull final MapType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final MapValueType ret = new MapValueType ();
    ret.setMap (x);
    return ret;
  }

  @Nonnull
  public static SlotValueType createSlotValue (@Nonnull final SlotType x)
  {
    ValueEnforcer.notNull (x, "Value");
    final SlotValueType ret = new SlotValueType ();
    ret.setSlot (x);
    return ret;
  }

  @Nonnull
  public static StringValueType createSlotValue (@Nonnull final String x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new StringValueType (x);
  }

  @Nonnull
  public static VocabularyTermValueType createSlotValue (@Nonnull final VocabularyTermType x)
  {
    ValueEnforcer.notNull (x, "Value");
    return new VocabularyTermValueType (x);
  }

  @Nonnull
  public static SlotType createSlot (@Nonnull @Nonempty final String sName, @Nonnull final ValueType aValue)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aValue, "Value");

    final SlotType ret = new SlotType ();
    ret.setName (sName);
    ret.setSlotValue (aValue);
    return ret;
  }
}
