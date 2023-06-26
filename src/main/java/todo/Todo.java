package todo;

import org.bson.Document;
import org.bson.types.ObjectId;
import utils.Builder;

public class Todo implements Necessarys {

    private ObjectId id;
    private String text;
    private Boolean done;
    private String assignedTo; // VG-del

    public Todo() {
    }

    @Override
    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    @Override
    public void setId(String id) {
        this.id = id != null ? new ObjectId(id) : null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public static Todo fromDoc(Document document) {
        Todo todo = new Todo();
        todo.setId(document.getObjectId("_id").toString());
        todo.setText(document.getString("text"));
        todo.setDone(document.getBoolean("done"));
        return todo;
    }

    public Document toDoc() {
        Document document = new Document();
        if (id != null) {
            document.append("_id", id);
        }
        document.append("text", text).append("done", done);
        return document;
    }
}