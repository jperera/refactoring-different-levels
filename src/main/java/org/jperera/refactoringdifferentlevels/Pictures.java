package org.jperera.refactoringdifferentlevels;

public class Pictures {
    private Iterable<Picture> pictures;

    public Pictures(Iterable<Picture> pictures) {
        this.pictures = pictures;
    }

    public Iterable<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Iterable<Picture> pictures) {
        this.pictures = pictures;
    }
}
