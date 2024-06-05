package model.repository;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskRepository taskRepository;
    private final AuthenticationRepository authenticationRepository;
    private final SkillRepository skillRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final EntryRepository entryRepository;
    private final NotificationRepository notificationRepository;
    private final TeamRepository teamRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskRepository = new TaskRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository=new CollaboratorRepository();
        jobRepository=new JobRepository();
        greenSpaceRepository=new GreenSpaceRepository();
        entryRepository=new EntryRepository();
        notificationRepository=new NotificationRepository();
        teamRepository=new TeamRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {return organizationRepository;}

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public SkillRepository getSkillRepository() { return skillRepository; }

    public CollaboratorRepository getCollaboratorRepository() {return collaboratorRepository;}

    public JobRepository getJobRepository() {return jobRepository;}

    public GreenSpaceRepository getGreenSpaceRepository() {return greenSpaceRepository;}

    public EntryRepository getToDoList() {return entryRepository;}

    public TaskRepository getTaskRepository() { return taskRepository;}

    public NotificationRepository getNotificationRepository() { return notificationRepository;}

    public EntryRepository getEntryRepository(){return entryRepository;}

    public TeamRepository getTeamRepository() {return teamRepository;}
}