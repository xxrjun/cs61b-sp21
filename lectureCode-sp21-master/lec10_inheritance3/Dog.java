package lec10_inheritance3;

public class Dog implements Comparable<Dog> {
    public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    /**
     * Natural order
     *
     * @param uddaDog the object to be compared.
     * @return negative number if o1 < o2.
     *         0 if o1 equals o2.
     *         positive number if o1 > o2.
     */
    @Override
    public int compareTo(Dog uddaDog) {
        //assume nobody is messing up and giving us
        //something that isn't a dog.
        return size - uddaDog.size;
    }

    /**
     *  We've declared NameComparator to be a static class.
     *  A minor difference, but we do so because we do not need to instantiate a Dog to
     *  get a NameComparator
     */
    private static class NameComparator implements Comparator<Dog>{
        public int compare(Dog a, Dog b){
            return a.name.compareTo(b.name);
        }
    }

    public static Comparator<Dog> getNameComparator(){
        return new NameComparator();
    }

    public void bark() {
        System.out.println(name + " says: bark");
    }
}