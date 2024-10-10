package com.mule.mulechain.internal.internal;

import static org.apache.commons.io.IOUtils.toInputStream;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

import io.github.thoroldvix.api.*;
import io.github.thoroldvix.internal.TranscriptApiFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class YouTubeTranscriptsOperations {

  YoutubeTranscriptApi youtubeTranscriptApi = TranscriptApiFactory.createDefault();


  /**
   * Gets a transcript for a youtube video by video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Fetch-transcript")
  public InputStream getTranscript(String videoId, String language, @Config YouTubeTranscriptsConfiguration configuration) throws TranscriptRetrievalException {

    TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);
    Transcript transcript = transcriptList.findTranscript(language);
    TranscriptContent transcriptContent = transcript.fetch();
    TranscriptFormatter jsonFormatter = TranscriptFormatters.jsonFormatter();
    String formattedContent = jsonFormatter.format(transcriptContent);

    return toInputStream(formattedContent, StandardCharsets.UTF_8);
  }

  /**
   * Gets meta data for a transcript by youtube video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Get-metadata")
  public InputStream getMetadata(String videoId, @Config YouTubeTranscriptsConfiguration configuration) throws TranscriptRetrievalException {

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
  }



  /**
   * Gets a transcript for a youtube video by video Id (https://www.youtube.com/watch?v=h4iG90zkrig, everthing after v= is the videoId)
   */
  @MediaType(value = APPLICATION_JSON, strict = false)
  @Alias("Translate-transcript")
  public InputStream getTranscriptTranslated(String videoId, String fromLanguage, String toLanguage, @Config YouTubeTranscriptsConfiguration configuration) throws TranscriptRetrievalException {

    TranscriptList transcriptList = youtubeTranscriptApi.listTranscripts(videoId);
    Transcript transcript = transcriptList.findTranscript(fromLanguage).translate(toLanguage);
    TranscriptContent transcriptContent = transcript.fetch();
    TranscriptFormatter jsonFormatter = TranscriptFormatters.jsonFormatter();
    String formattedContent = jsonFormatter.format(transcriptContent);

    return toInputStream(formattedContent, StandardCharsets.UTF_8);
  }

}
