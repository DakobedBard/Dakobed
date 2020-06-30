package org.mddarr.dakobedtabsservice.models;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Transcription {

    public List<Note> notes;

    public Transcription(String transcription_file_path) throws IOException {
        notes = new ArrayList<>();
        JsonParser parser = new JsonFactory().createParser(new File(transcription_file_path));
        JsonNode rootNode = new ObjectMapper().readTree(parser);
        Iterator<JsonNode> iter = rootNode.iterator();
        ObjectNode currentNode;

        while (iter.hasNext()) {
            currentNode = (ObjectNode) iter.next();
            int beat = currentNode.path("beat").asInt();
            int measure = currentNode.path("measure").asInt();
            int midi = currentNode.path("midi").asInt();
            int string = currentNode.path("string").asInt();
            Note note = new Note(midi, beat, measure, string);
            notes.add(note);
        }
    }
}


class Note {
    int midi;
    int beat;
    int measure;
    int string;

    public Note(int midi, int beat, int measure, int string) {
        this.midi = midi;
        this.beat = beat;
        this.measure = measure;
        this.string = string;
    }

    public int getMidi() { return midi;}
    public void setMidi(int midi) {  this.midi = midi;}

    public int getBeat() {return beat;}
    public void setBeat(int beat) {  this.beat = beat; }

    public int getMeasure() {  return measure; }
    public void setMeasure(int measure) {this.measure = measure;}

    public int getString() {
        return string;
    }

    public void setString(int string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "Note{" +
                "midi=" + midi +
                ", beat=" + beat +
                ", measure=" + measure +
                ", string=" + string +
                '}';
    }
}
