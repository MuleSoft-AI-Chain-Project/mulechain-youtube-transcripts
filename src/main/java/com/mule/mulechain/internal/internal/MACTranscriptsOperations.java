package com.mule.mulechain.internal.internal;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

import io.github.thoroldvix.api.*;
import io.github.thoroldvix.internal.TranscriptApiFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class MACTranscriptsOperations {

  YoutubeTranscriptApi youtubeTranscriptApi = TranscriptApiFactory.createDefault();


  /**
   * Gets a transcript for a youtube video by video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Fetch-transcript")
  public InputStream getTranscript(String videoId, String language, @Config MACTranscriptsConfiguration configuration) throws TranscriptRetrievalException {

    try {
      TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);
      Transcript transcript = transcriptList.findTranscript(language);
      TranscriptContent transcriptContent = transcript.fetch();
      TranscriptFormatter jsonFormatter = TranscriptFormatters.jsonFormatter();
      String formattedContent = jsonFormatter.format(transcriptContent);

      return toInputStream(formattedContent, StandardCharsets.UTF_8);
    } catch (TranscriptRetrievalException e) {
      // Build a JSON response for the error
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "Could not retrieve transcript");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());  // The exception message can be used as the reason

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);

    } catch (Exception e) {
      // Catch any other exception and return a generic error response
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "An unexpected error occurred");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);
    }
  }

  /**
   * Gets meta data for a transcript by youtube video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Get-metadata")
  public InputStream getMetadata(String videoId, @Config MACTranscriptsConfiguration configuration) throws TranscriptRetrievalException {
    try{
      TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);
      JSONObject jsonObject = new JSONObject();
      JSONArray transcripts = new JSONArray();
      JSONObject metadata;

      for(Transcript transcript : transcriptList) {
        metadata = new JSONObject();
        metadata.put("language", transcript.getLanguage());
        metadata.put("languageCode", transcript.getLanguageCode());
        metadata.put("translations", transcript.getTranslationLanguages());
        metadata.put("apiUrl", transcript.getApiUrl());

        transcripts.put(metadata);
      }

      jsonObject.put("videoId", transcriptList.getVideoId());
      jsonObject.put("transcripts", transcripts);

      return toInputStream(jsonObject.toString(), StandardCharsets.UTF_8);
    } catch (TranscriptRetrievalException e) {
      // Build a JSON response for the error
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "Could not retrieve transcript");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);

    } catch (IllegalArgumentException e) {

      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "Invalid video ID");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);

    } catch (Exception e) {
      // Catch any other exception and return a generic error response
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "An unexpected error occurred");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);
    }

  }



  /**
   * Gets a transcript for a youtube video by video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Translate-transcript")
  public InputStream getTranscriptTranslated(String videoId, @Config MACTranscriptsConfiguration configuration, @ParameterGroup(name = "Additional Properties") MACTranscriptsLanguageParameterProvider toLanguage) throws TranscriptRetrievalException {
    try{

      String code = extractValueWithinBrackets(toLanguage.getLanguages());

      TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);
      Transcript transcript = transcriptList.findGeneratedTranscript("en").translate(code);
      TranscriptContent transcriptContent = transcript.fetch();
      TranscriptFormatter jsonFormatter = TranscriptFormatters.jsonFormatter();
      String formattedContent = jsonFormatter.format(transcriptContent);

      return toInputStream(formattedContent, StandardCharsets.UTF_8);
    } catch (TranscriptRetrievalException e) {
      // Build a JSON response for the error
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "Could not retrieve transcript");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());  // The exception message can be used as the reason

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);

    } catch (Exception e) {
      // Catch any other exception and return a generic error response
      JSONObject errorJson = new JSONObject();
      errorJson.put("error", "An unexpected error occurred");
      errorJson.put("videoId", videoId);
      errorJson.put("reason", e.getMessage());

      String jsonErrorResponse = errorJson.toString();
      return toInputStream(jsonErrorResponse, StandardCharsets.UTF_8);
    }
  }


  // Method to extract value within brackets
  private static String extractValueWithinBrackets(String input) {
    // Find the position of the opening and closing brackets
    int start = input.indexOf('(');
    int end = input.indexOf(')');

    // Extract and return the substring within the brackets
    if (start != -1 && end != -1 && start < end) {
      return input.substring(start + 1, end);
    }
    return null;  // Return null if no brackets found
  }
}
