package model.repository;

import model.Collaborator;
import model.Entry;
import model.GreenSpace;
import model.Task;

import java.time.Duration;
import java.util.*;

public class EntryRepository {
    private Map<String, List<Entry>> toDoList;
    private Map<String, List<Entry>> agenda;
    private List<Entry> entries;

    public EntryRepository() {
        this.toDoList = new HashMap<>();
        this.agenda = new HashMap<>();
    }

    public boolean addEntryToDoList(Task task, Duration duration, String urgency, GreenSpace greenSpace, String userName) {
        Entry entry = new Entry(task, duration, urgency, greenSpace);
        if (!toDoList.containsKey(userName)) {
            List<Entry> l = new ArrayList<>();
            l.add(entry);
            toDoList.put(userName, l);
            return true;
        }

        if (toDoList.get(userName).contains(entry)) {
            return false;
        }
        toDoList.get(userName).add(entry);
        return true;
    }

    public List<Entry> getToDoList(String userName) {
        return List.copyOf(toDoList.getOrDefault(userName, Collections.emptyList()));
    }

    public boolean addAgenda(Entry entry, Date startingDate, String userName) {
        if(entry.schedule(startingDate)) {
            if (!agenda.containsKey(userName)) {
                List<Entry> l = new ArrayList<>();
                l.add(entry);
                agenda.put(userName, l);
                return true;
            }

            if (agenda.get(userName).contains(entry)) {
                return false;
            }
            agenda.get(userName).add(entry);
            return true;
        }
        return false;
    }

    public List<Entry> getAllEntriesForUser(String userName) {
        List<Entry> allEntries = new ArrayList<Entry>();
        if (agenda.containsKey(userName)) {
            allEntries.addAll(agenda.get(userName));
        }
        return allEntries;
    }

    public List<Entry> getEntriesForCollaborator(Collaborator collaborator) {
        List<Entry> result= new ArrayList<>();
        for (List<Entry> entries1 : agenda.values()){
            for (Entry e: entries1){
                if (e.getTeam().getCollaborators().contains(collaborator))
                    result.add(e);
            }
        }
        return result;}
}
