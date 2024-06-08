package com.yunusbagriyanik.model;

import java.util.Date;

public class Tomatoes {
    private Viewer viewer;
    private Date lastupdated;

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Date getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Date lastupdated) {
        this.lastupdated = lastupdated;
    }
}
