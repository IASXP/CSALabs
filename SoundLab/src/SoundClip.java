import java.util.*;
import java.io.*;

public class SoundClip {

    double[] clip;
    int asdfwe = 0;

    public SoundClip() {
        clip = null;
    }

    public SoundClip(double[] SoundClip)
    {
        clip = SoundClip;
    }

    void adjustVolume(double factor) {
        for (int idx = 0; idx < clip.length; idx++)
        {
            clip[idx] *= factor;
        }
    }

    void mix(double[] clip1, double[] clip2)
    {
        double[] combinedClip;
        if (clip1.length > clip2.length) {

            combinedClip = new double[clip1.length];
        } else {

            combinedClip = new double[clip2.length];
        }

        int minLength = Math.min(clip1.length, clip2.length);
        for (int idx = 0; idx < minLength; idx++) {
            combinedClip[idx] = clip1[idx] + clip2[idx];
        }

        if (clip1.length > clip2.length) {
            for (int idx = minLength; idx < clip1.length; idx++) {
                combinedClip[idx] = clip1[idx];
            }
        } else {
            for (int idx = minLength; idx < clip2.length; idx++) {
                combinedClip[idx] = clip2[idx];
            }
        }

        clip = combinedClip;
    }

    void append(String FileName) throws IOException
    {
        File audioFile = new File(FileName);

        Scanner scanner = new Scanner(audioFile);
        int additionalSamples = scanner.nextInt();

        asdfwe = additionalSamples;
        double[] extendedClip = new double[clip.length + additionalSamples];

        for (int idx = 0; idx < clip.length; idx++) {
            extendedClip[idx] = clip[idx];
        }
        for (int idx = clip.length; idx < extendedClip.length; idx++) {
            extendedClip[idx] = scanner.nextDouble();
        }
        clip = extendedClip;
    }

    void fadeIn(double seconds)
    {
        double samplesCount = Sound.toNumSamples(seconds);
        for (int idx = 0; idx < samplesCount; idx++) {

            clip[idx] = (idx / samplesCount) * clip[idx];
        }
    }

    void fadeOut(double seconds)
    {
        double samplesCount = Sound.toNumSamples(seconds);
        for (int idx = 0; idx < samplesCount; idx++)
        {

            clip[clip.length - idx - 1] = (idx / samplesCount) * clip[clip.length - idx - 1];
        }
    }

    void reverse()
    {
        double[] flippedClip = new double[clip.length];
        for (int idx = 0; idx < clip.length; idx++)
        {
            flippedClip[idx] = clip[clip.length - idx - 1];
            //flippedClip[idx] = clip[clip.length];
        }
        clip = flippedClip;
    }

    void speedUp(double factor) throws IOException
    {
        double[] fasterClip = new double[(int)(clip.length / factor)];

        for (int idx = 0; idx < fasterClip.length; idx++)
        {
            fasterClip[idx] = clip[(int) (idx * factor)];

        }

        clip = fasterClip;
    }
}
