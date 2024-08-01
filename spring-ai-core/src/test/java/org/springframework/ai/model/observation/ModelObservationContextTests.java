package org.springframework.ai.model.observation;

import org.junit.jupiter.api.Test;
import org.springframework.ai.observation.AiOperationMetadata;
import org.springframework.ai.observation.conventions.AiOperationType;
import org.springframework.ai.observation.conventions.AiProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link ModelObservationContext}.
 *
 * @author Thomas Vitale
 */
class ModelObservationContextTests {

	@Test
	void whenRequestAndMetadataThenReturn() {
		var observationContext = new ModelObservationContext<String, String>("test request",
				AiOperationMetadata.builder()
					.operationType(AiOperationType.CHAT.value())
					.provider(AiProvider.OLLAMA.value())
					.build());

		assertThat(observationContext).isNotNull();
	}

	@Test
	void whenRequestIsNullThenThrow() {
		assertThatThrownBy(() -> new ModelObservationContext<String, String>(null,
				AiOperationMetadata.builder()
					.operationType(AiOperationType.EMBEDDING.value())
					.provider(AiProvider.OLLAMA.value())
					.build()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("request cannot be null");
	}

	@Test
	void whenOperationMetadataIsNullThenThrow() {
		assertThatThrownBy(() -> new ModelObservationContext<String, String>("test request", null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("operationMetadata cannot be null");
	}

	@Test
	void whenOperationMetadataIsMissingOperationTypeThenThrow() {
		assertThatThrownBy(() -> new ModelObservationContext<String, String>("test request",
				AiOperationMetadata.builder().provider(AiProvider.OLLAMA.value()).build()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("operationType cannot be null or empty");
	}

	@Test
	void whenOperationMetadataIsMissingProviderThenThrow() {
		assertThatThrownBy(() -> new ModelObservationContext<String, String>("test request",
				AiOperationMetadata.builder().operationType(AiOperationType.IMAGE.value()).build()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("provider cannot be null or empty");
	}

	@Test
	void whenResponseThenReturn() {
		var observationContext = new ModelObservationContext<String, String>("test request",
				AiOperationMetadata.builder()
					.operationType(AiOperationType.CHAT.value())
					.provider(AiProvider.OLLAMA.value())
					.build());
		observationContext.setResponse("test response");

		assertThat(observationContext).isNotNull();
	}

	@Test
	void whenResponseIsNullThenThrow() {
		var observationContext = new ModelObservationContext<String, String>("test request",
				AiOperationMetadata.builder()
					.operationType(AiOperationType.CHAT.value())
					.provider(AiProvider.OLLAMA.value())
					.build());
		assertThatThrownBy(() -> observationContext.setResponse(null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("response cannot be null");
	}

}
