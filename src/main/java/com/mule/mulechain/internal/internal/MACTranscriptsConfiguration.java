package com.mule.mulechain.internal.internal;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.sdk.api.annotation.param.Optional;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(MACTranscriptsOperations.class)
public class MACTranscriptsConfiguration {

  @Parameter
  @Optional(defaultValue = "optional-currently-no-value-needed")
  private String apiKey;

  public String getApiKey(){
    return apiKey;
  }
}
