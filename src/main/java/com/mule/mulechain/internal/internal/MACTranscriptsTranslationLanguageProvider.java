package com.mule.mulechain.internal.internal;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

import java.util.Set;

public class MACTranscriptsTranslationLanguageProvider implements ValueProvider {

    @Override
    public Set<Value> resolve() throws ValueResolvingException {
        return ValueBuilder.getValuesFor(
                "Hindi (hi)",
                "Pashto (ps)",
                "Portuguese (pt)",
                "Luo (luo)",
                "Filipino (fil)",
                "Hmong (hmn)",
                "Croatian (hr)",
                "Haitian Creole (ht)",
                "Hungarian (hu)",
                "Tumbuka (tum)",
                "Yiddish (yi)",
                "Armenian (hy)",
                "Chinese Simplified (zh-Hans)",
                "Chinese Traditional (zh-Hant)",
                "Yoruba (yo)",
                "Indonesian (id)",
                "Igbo (ig)",
                "Afar (aa)",
                "Abkhazian (ab)",
                "Quechua (qu)",
                "Afrikaans (af)",
                "Akan (ak)",
                "Icelandic (is)",
                "Italian (it)",
                "Amharic (am)",
                "Hebrew (iw)",
                "Arabic (ar)",
                "Assamese (as)",
                "Portuguese (Portugal) (pt-PT)",
                "Japanese (ja)",
                "Aymara (ay)",
                "Azerbaijani (az)",
                "Zulu (zu)",
                "Kirundi (rn)",
                "Romanian (ro)",
                "Bashkir (ba)",
                "Cebuano (ceb)",
                "Belarusian (be)",
                "Russian (ru)",
                "Bulgarian (bg)",
                "Kinyarwanda (rw)",
                "Northern Sotho (nso)",
                "Bengali (bn)",
                "Javanese (jv)",
                "Tibetan (bo)",
                "Sanskrit (sa)",
                "Breton (br)",
                "Bosnian (bs)",
                "Sindhi (sd)",
                "Sango (sg)",
                "Georgian (ka)",
                "Sinhala (si)",
                "Slovak (sk)",
                "Slovenian (sl)",
                "Samoan (sm)",
                "Shona (sn)",
                "Somali (so)",
                "Albanian (sq)",
                "Catalan (ca)",
                "Serbian (sr)",
                "Kazakh (kk)",
                "Swati (ss)",
                "Greenlandic (kl)",
                "Southern Sotho (st)",
                "Khmer (km)",
                "Sundanese (su)",
                "Kannada (kn)",
                "Swedish (sv)",
                "Korean (ko)",
                "Swahili (sw)",
                "Kurdish (ku)",
                "Corsican (co)",
                "Tamil (ta)",
                "Kyrgyz (ky)",
                "Czech (cs)",
                "Telugu (te)",
                "Tajik (tg)",
                "Thai (th)",
                "Latin (la)",
                "Tigrinya (ti)",
                "Luxembourgish (lb)",
                "Welsh (cy)",
                "Turkmen (tk)",
                "Tswana (tn)",
                "Ganda (lg)",
                "Tongan (to)",
                "Danish (da)",
                "Turkish (tr)",
                "Tsonga (ts)",
                "Tatar (tt)",
                "German (de)",
                "Lingala (ln)",
                "Lao (lo)",
                "Lithuanian (lt)",
                "Latvian (lv)",
                "Dhivehi (dv)",
                "Uyghur (ug)",
                "Dzongkha (dz)",
                "Ukrainian (uk)",
                "Malagasy (mg)",
                "Maori (mi)",
                "Kapampangan (pam)",
                "Urdu (ur)",
                "Macedonian (mk)",
                "Hawaiian (haw)",
                "Malayalam (ml)",
                "Ewe (ee)",
                "Mongolian (mn)",
                "Newari (new)",
                "Khasi (kha)",
                "Marathi (mr)",
                "Uzbek (uz)",
                "Malay (ms)",
                "Greek (el)",
                "Maltese (mt)",
                "English (en)",
                "Esperanto (eo)",
                "Burmese (my)",
                "Spanish (es)",
                "Estonian (et)",
                "Venda (ve)",
                "Basque (eu)",
                "Vietnamese (vi)",
                "Nepali (ne)",
                "Persian (fa)",
                "Dutch (nl)",
                "Norwegian (no)",
                "Finnish (fi)",
                "Fijian (fj)",
                "Ga (gaa)",
                "Faroese (fo)",
                "Chichewa (ny)",
                "French (fr)",
                "Frisian (fy)",
                "Occitan (oc)",
                "Wolof (wo)",
                "Irish (ga)",
                "Scottish Gaelic (gd)",
                "Oromo (om)",
                "Krio (kri)",
                "Oriya (or)",
                "Ossetian (os)",
                "Galician (gl)",
                "Seychellois Creole (crs)",
                "Guarani (gn)",
                "Bhojpuri (bho)",
                "Waray (war)",
                "Mauritian Creole (mfe)",
                "Gujarati (gu)",
                "Manx (gv)",
                "Xhosa (xh)",
                "Punjabi (pa)",
                "Hausa (ha)",
                "Polish (pl)"
        );
    }

}
