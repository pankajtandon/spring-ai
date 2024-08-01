/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.ai.embedding.observation;

import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.model.observation.ModelObservationContext;
import org.springframework.ai.observation.AiOperationMetadata;
import org.springframework.util.Assert;

/**
 * Context used to store metadata for embedding model exchanges.
 *
 * @author Thomas Vitale
 * @since 1.0.0
 */
public class EmbeddingModelObservationContext extends ModelObservationContext<EmbeddingRequest, EmbeddingResponse> {

	private final EmbeddingModelRequestOptions requestOptions;

	EmbeddingModelObservationContext(EmbeddingRequest embeddingRequest, AiOperationMetadata operationMetadata,
			EmbeddingModelRequestOptions requestOptions) {
		super(embeddingRequest, operationMetadata);
		Assert.notNull(requestOptions, "requestOptions cannot be null");
		this.requestOptions = requestOptions;
	}

	public EmbeddingModelRequestOptions getRequestOptions() {
		return requestOptions;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private EmbeddingRequest embeddingRequest;

		private AiOperationMetadata operationMetadata;

		private EmbeddingModelRequestOptions requestOptions;

		private Builder() {
		}

		public Builder embeddingRequest(EmbeddingRequest embeddingRequest) {
			this.embeddingRequest = embeddingRequest;
			return this;
		}

		public Builder operationMetadata(AiOperationMetadata operationMetadata) {
			this.operationMetadata = operationMetadata;
			return this;
		}

		public Builder requestOptions(EmbeddingModelRequestOptions requestOptions) {
			this.requestOptions = requestOptions;
			return this;
		}

		public EmbeddingModelObservationContext build() {
			return new EmbeddingModelObservationContext(embeddingRequest, operationMetadata, requestOptions);
		}

	}

}
