package com.example.workout;

import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public void clearQueue() {
        updates.clear();
        saveButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Set<Integer> ids = new HashSet<Integer>();
        ids.addAll(updates.keySet());
        for (Integer id: ids) {
            Integer weight = updates.remove(id);
            AppWideResourceWrapper.getSqlitedb().execSQL(AppWideResourceWrapper.getGlobalContext().getString(R.string.updateWeightQuery), new Object[]{weight, id});
        }
        saveButton.setVisibility(View.INVISIBLE);
    }
}