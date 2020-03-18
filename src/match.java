import java.util.ArrayList;
import java.util.LinkedList;

public class match {
    public static void main(String[] args) {
        ArrayList<Man> men;
        ArrayList<Woman> women;
        LinkedList<Man> menTmp;
        Integer[] current;
        
        People.init();
        Man Adam = new Man("Adam");
        Man Bill = new Man("Bill");
        Man Carl = new Man("Carl");
        Man Dan = new Man("Dan");
        Man Eric = new Man("Eric");
        People.init();
        Woman Amy = new Woman("Amy");
        Woman Beth = new Woman("Beth");
        Woman Cara = new Woman("Cara");
        Woman Diane = new Woman("Diane");
        Woman Ellen = new Woman("Ellen");
        men = new ArrayList<>();
        men.add(Adam.setPrefers(Beth, Amy, Diane, Ellen, Cara));
        men.add(Bill.setPrefers(Diane, Beth, Amy, Cara, Ellen));
        men.add(Carl.setPrefers(Beth, Ellen, Cara, Diane, Amy));
        men.add(Dan.setPrefers(Amy, Diane, Cara, Beth, Ellen));
        men.add(Eric.setPrefers(Beth, Diane, Amy, Ellen, Cara));
        women = new ArrayList<>();
        women.add(Amy.setPrefers(Eric, Adam, Bill, Dan, Carl));
        women.add(Beth.setPrefers(Carl, Bill, Dan, Adam, Eric));
        women.add(Cara.setPrefers(Bill, Carl, Dan, Eric, Adam));
        women.add(Diane.setPrefers(Adam, Eric, Dan, Carl, Bill));
        women.add(Ellen.setPrefers(Dan, Bill, Eric, Carl, Adam));
        menTmp = new LinkedList<>();
        for (Man m : men) {
            menTmp.push(m);
        }
        current = new Integer[men.size()];
        for (int i = 0; i < men.size(); i++) {
            current[i] = null;
        }
        while (!menTmp.isEmpty()) {
            Man man = menTmp.pop();
            int prefer = man.next();
            if (prefer != -1) {
                if (current[prefer] == null) {
                    current[prefer] = man.getID();
                } else {
                    if (women.get(prefer).prefer(current[prefer], man.getID())) {
                        menTmp.push(men.get(current[prefer]));
                        current[prefer] = man.getID();
                    } else {
                        menTmp.push(man);
                    }
                }
            }
        }
        for (int i = 0; i < current.length; i++) {
            System.out.print(women.get(i));
            System.out.print("-");
            System.out.println(men.get(current[i]));
        }
    }
}

class People {
    private String name;
    protected int ID;
    protected static int curID = 0;
    
    public static void init() {
        curID = 0;
    }
    
    public People(String name) {
        this.name = name;
    }
    
    public int getID() {
        return ID;
    }
    
    @Override
    public String toString() {
        return name;
    }
}

class Man extends People {
    private int[] prefers;
    private int index;
    
    public Man(String name) {
        super(name);
        this.ID = curID;
        curID++;
    }
    
    public Man setPrefers(Woman... women) {
        prefers = new int[women.length];
        for (int i = 0; i < women.length; i++) {
            prefers[i] = women[i].getID();
        }
        index = 0;
        return this;
    }
    
    public int next() {
        if (index < prefers.length) {
            return prefers[index++];
        } else {
            return -1;
        }
    }
}

class Woman extends People {
    private int[] prefers;
    
    public Woman(String name) {
        super(name);
        this.ID = curID;
        curID++;
    }
    
    public Woman setPrefers(Man... men) {
        prefers = new int[men.length];
        for (int i = 0; i < men.length; i++) {
            prefers[men[i].getID()] = i;
        }
        return this;
    }
    
    public boolean prefer(int cur, int next) {
        return prefers[next] < prefers[cur];
    }
}
