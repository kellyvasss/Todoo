package todo;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.bson.types.ObjectId;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    public void testFromDoc() {
        // Arrange
        Document document = new Document("_id", new ObjectId())
                .append("text", "Buy groceries")
                .append("done", false);

        // Act
        Todo todo = Todo.fromDoc(document);

        // Assert
        assertNotNull(todo);
        assertEquals(document.getObjectId("_id").toString(), todo.getId());
        assertEquals(document.getString("text"), todo.getText());
        assertEquals(document.getBoolean("done"), todo.getDone());
    }

    @Test
    public void testToDoc() {
        // Arrange
        Todo todo = new Todo();
        todo.setId("60c99b19f682c21ebcfc14c0");
        todo.setText("Buy groceries");
        todo.setDone(false);

        // Act
        Document document = todo.toDoc();

        // Assert
        assertNotNull(document);
        assertEquals(todo.getId(), document.getObjectId("_id").toString());
        assertEquals(todo.getText(), document.getString("text"));
        assertEquals(todo.getDone(), document.getBoolean("done"));
    }
}