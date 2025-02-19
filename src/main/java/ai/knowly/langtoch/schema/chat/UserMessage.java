package ai.knowly.langtoch.schema.chat;

/** A message from the user. */
public class UserMessage extends ChatMessage {

  public UserMessage(String content) {
    super(content, Role.USER);
  }

  public static UserMessage of(String content) {
    return new UserMessage(content);
  }

  @Override
  public String toString() {
    return String.format("%s: %s", getRole(), getContent());
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof UserMessage && ((UserMessage) obj).getContent().equals(getContent()))
        || (obj instanceof ChatMessage
            && ((ChatMessage) obj).getContent().equals(getContent())
            && ((ChatMessage) obj).getRole().equals(Role.USER));
  }
}
