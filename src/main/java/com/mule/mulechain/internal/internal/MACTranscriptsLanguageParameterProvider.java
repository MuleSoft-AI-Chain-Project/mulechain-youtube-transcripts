package com.mule.mulechain.internal.internal;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class MACTranscriptsLanguageParameterProvider {

    @Parameter
    @Expression(ExpressionSupport.SUPPORTED)
    @OfValues(MACTranscriptsTranslationLanguageProvider.class)
    private String languages;

    public String getLanguages() {
        return languages;
    }

}
