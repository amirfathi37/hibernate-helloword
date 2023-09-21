package org.fathi;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.fathi.entities.Workspace;
import org.hibernate.Hibernate;

import java.io.IOException;

public class WorkspaceTypeAdapter extends TypeAdapter<Workspace> {
    @Override
    public void write(JsonWriter out, Workspace workspace) throws IOException {
        if (workspace == null || Hibernate.isInitialized(workspace)) {
            out.nullValue();
            return;
        }

        // Serialize the workspace without its children to avoid circular reference
        workspace.setChilds(null);
        out.beginObject();
        out.name("id").value(workspace.getId());
        out.name("name").value(workspace.getName());
        // Add other properties as needed
        out.endObject();
    }

    @Override
    public Workspace read(JsonReader jsonReader) throws IOException {
        return null;
    }
}

