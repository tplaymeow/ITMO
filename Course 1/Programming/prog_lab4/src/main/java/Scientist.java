import java.util.Objects;

public class Scientist extends Human{
    private boolean isWorking;

    public Scientist(String name, Place bornAt, int intellectLevel) {
        super(name, bornAt, intellectLevel);
        isWorking = false;
    }

    class Work {
        private final String name;
        private final String description;

        public Work(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public void start() throws WorkException {
            if (isWorking) {
                throw new WorkException(getName() + " уже работает");
            }
            System.out.println(getName() + " начал работать " + this);
            isWorking = true;

        }

        public void end() throws WorkException {
            if (!isWorking)  {
                throw new WorkException(getName() + " не работает");
            }
            System.out.println(getName() + " решил прекратить работу");
            isWorking = false;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Work work = (Work) o;
            return Objects.equals(name, work.name) &&
                    Objects.equals(description, work.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, description);
        }

        @Override
        public String toString() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    public void rest() throws WorkException{
        if (isWorking) {
            throw new WorkException(this.getName() + " на работе");
        }
        System.out.println(this.getName() + " решил отдохнуть");
    }

    public void learn(Organism organism) {
        if (this.getLocation().equals(organism.getLocation())) {
            System.out.println(this.getName() + " изучает " + organism.getName());
        }
    }

    public void cover(Organism organism) {
        System.out.println(this.getName() + " накрыл брезентом " + organism.getName());
    }

}
