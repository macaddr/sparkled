package net.chrisparton.sparkled.renderer.data;

import java.util.ArrayList;
import java.util.List;

public class AnimationFrame {

    int frameNumber;
    private final List<Led> leds;

    /**
     * Default constructor required for Gson.
     */
    @SuppressWarnings("unused")
    public AnimationFrame() {
        this(0, 0);
    }

    public AnimationFrame(int frameNumber, int ledCount) {
        this.frameNumber = frameNumber;
        this.leds = new ArrayList<>(ledCount);

        for (int i = 0; i < ledCount; i++) {
            leds.add(new Led());
        }
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public List<Led> getLeds() {
        return leds;
    }
}
