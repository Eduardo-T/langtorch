package ai.knowly.langtoch.llm.processor.openai.chat;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import ai.knowly.langtoch.llm.integration.openai.service.OpenAIService;
import ai.knowly.langtoch.schema.chat.AssistantMessage;
import ai.knowly.langtoch.schema.chat.ChatMessage;
import ai.knowly.langtoch.schema.chat.Role;
import ai.knowly.langtoch.schema.chat.UserMessage;
import ai.knowly.langtoch.schema.io.MultiChatMessage;
import ai.knowly.langtoch.util.OpenAIServiceTestingUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
final class OpenAIChatProcessorTest {
  @Mock private OpenAIService openAIService;
  private OpenAIChatProcessor openAIChatProcessor;

  @BeforeEach
  public void setUp() {
    openAIChatProcessor = new OpenAIChatProcessor(openAIService);
  }

  @Test
  void testRunWithChatProcessorConfig() {
    // Arrange
    List<ChatMessage> messages =
        Arrays.asList(
            UserMessage.of("What is the weather today?"),
            AssistantMessage.of("The weather today is sunny."));

    when(openAIService.createChatCompletion(any()))
        .thenReturn(
            OpenAIServiceTestingUtils.ChatCompletion.createChatCompletionResult(
                AssistantMessage.of("It's going to be a hot day.")));

    // Act
    ChatMessage output = openAIChatProcessor.run(MultiChatMessage.of(messages));

    // Assert
    assertThat(output.getRole()).isEqualTo(Role.ASSISTANT);
    assertThat(output.getContent()).isEqualTo("It's going to be a hot day.");
  }
}
