package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;

public class Entry {
    private  Task task;
    private Duration duration;
    private String urgency;
    private GreenSpace greenSpace;
    private String status;
    private Date date;
    private Team team;
    private static final String DEFAULT_STATUS="pending";

    public Entry(Task task, Duration duration, String urgency, GreenSpace greenSpace) {
        this.task=task;
        this.duration = duration;
        this.urgency = urgency;
        this.greenSpace = greenSpace;
        this.status=DEFAULT_STATUS;
        this.date=null;
        this.team=null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry e = (Entry) o;
        return task.equals(e.task) && duration.equals(e.duration) &&  urgency.equals(e.urgency) && greenSpace.equals(e.greenSpace);
    }


    @Override
    public int hashCode() {
        return Objects.hash(task, duration, urgency, greenSpace);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Entry clone() {
        return new Entry(this.task, this.duration, this.urgency, this.greenSpace);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @param startingDate starting date in the agenda
     * @return true if the entry has been succesfully scheduled and never been scheduled yet, false in other case
     */
    public boolean schedule(Date startingDate){
        if(this.status==DEFAULT_STATUS && this.team==null && this.date==null ) {
            this.status = "planned";
            this.date = startingDate;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean assignTeam(Team team) {
        if (team == null) {
            return false;
        }
        this.team = team;
        this.status = "Assigned";
        return true;
    }

    @Override
    public String toString() {
        return task +", " + duration +", " + urgency + ", " + greenSpace + ", status=" + status + ", date=" + date + ", team=" + team;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}