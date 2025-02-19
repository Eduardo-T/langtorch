package ai.knowly.langtoch.llm.integration.openai.service.schema.embedding;

import java.util.List;
import lombok.Data;

/**
 * Represents an embedding returned by the embedding api
 *
 * <p>https://beta.openai.com/docs/api-reference/classifications/create
 */
@Data
public class Embedding {

  /** The type of object returned, should be "embedding" */
  String object;

  /** The embedding vector */
  List<Double> embedding;

  /** The position of this embedding in the list */
  Integer index;
}
