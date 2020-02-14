package com.example.workout;

import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class SaveButtonClickListener implements View.OnClickListener {
    Map<Integer, Integer> updates = new HashMap<Integer, Integer>();
    Button saveButton;
    public SaveButtonClickListener(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Integer enqueueUpdate(Integer id, Integer weight) {
        saveButton.setVisibility(View.VISIBLE);
        Integer old = updates.get(id);
        updates.put(id, weight);
        return old;
    }

    @Override
    public void onClick(View v) {
        for (Integer id: updates.keySet()) {
            Integer weight = updates.remove(id);
            AppWideResourceWrapper.getSqlitedb().execSQL("UPDATE Strength SET Weight=? WHERE Id=?", new Object[]{weight, id});
        }
        saveButton.setVisibility(View.INVISIBLE);
    }
}