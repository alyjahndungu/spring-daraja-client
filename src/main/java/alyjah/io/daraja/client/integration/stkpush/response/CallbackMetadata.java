package alyjah.io.daraja.client.integration.stkpush.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CallbackMetadata(@JsonProperty("Item") List<Item> item) {}
