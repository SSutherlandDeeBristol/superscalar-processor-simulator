package com.ssutherlanddee;

import java.util.ArrayList;
import java.util.List;

public class TagManager {

    public enum TagState {FREE, USED}

    private List<TagState> tags;

    public TagManager() {
        this.tags = new ArrayList<>();
    }

    public Integer getFreeTag() {
        for (Integer i = 0; i < tags.size(); ++i) {
            if (tags.get(i) == TagState.FREE)
                return i;
        }

        Integer tag = tags.size();
        tags.add(tag, TagState.USED);
        return tag;
    }

    public void freeTag(Integer tag) {
        tags.set(tag, TagState.FREE);
    }
}