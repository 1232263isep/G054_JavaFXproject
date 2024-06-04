package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.time.Duration;
import java.util.*;

public class EntryRepository {
    private Map<String, List<Entry>> toDoList;
    private Map<String, List<Entry>> agenda;

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
        entry.schedule(startingDate);
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

    public List<Entry> getAllEntriesForUser(String userName) {
        List<Entry> allEntries = new ArrayList<>(getToDoList(userName));
        if (agenda.containsKey(userName)) {
            allEntries.addAll(agenda.get(userName));
        }
        return allEntries;
    }
}
