package se.iftac.lnn.chatlab.core;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime started;
    private LocalDateTime ended;

    public Session() {

    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        started = started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        ended = ended;
    }
}
