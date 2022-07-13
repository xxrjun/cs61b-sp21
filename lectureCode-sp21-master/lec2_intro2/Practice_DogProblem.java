package lec2_intro2;

/**
 * Created by hug.
 */
public class Practice_DogProblem {
    public static Dog[] largerThanFourNeighbors(Dog[] dogs) {
        Dog[] returnDogs = new Dog[dogs.length];
        int cnt = 0;

        for(int i = 0; i < dogs.length; i++) {
            int compareIndex = i;
            if (isLargerThanFourNeighbors(compareIndex, dogs)) {
                returnDogs[cnt++] = dogs[compareIndex];
            }
        }

        returnDogs = arrayWithNoNulls(returnDogs, cnt);

        return returnDogs;
    }

    /* Helper Function: Check is biggest in four neighbor*/
    private static boolean isLargerThanFourNeighbors(int compareIndex, Dog[] dogs){
        for(int j = -2; j <= 2; j++){
            /* Check the index is in a valid bound */
            if(compareIndex + j >= 0 && compareIndex + j < dogs.length){
                if(j != 0) {
                    /* Assure that we do not compare ourselves to ourselves */
                    if(dogs[compareIndex].weightInPounds < dogs[compareIndex + j].weightInPounds){
                        return false;
                    }

                } else {
                    /* Avoid compare ourselves to ourselves */
                    continue;
                }
            } else {
                /* The index is out of bound */
                continue;
            }
        }

        return true;
    }

    /* Helper Function: Store only non-null item */
    private static Dog[] arrayWithNoNulls(Dog[] dogs, int length) {
        Dog[] returnDogs = new Dog[length];
        for(int i = 0; i < length; i++) {
            returnDogs[i] = dogs[i];
        }

        return returnDogs;
    }



    public static void main(String[] args) {
        Dog[] dogs = new Dog[]{
                new Dog(10),
                new Dog(15),
                new Dog(20),
                new Dog(15),
                new Dog(10),
                new Dog(5),
                new Dog(10),
                new Dog(15),
                new Dog(22),
                new Dog(15),
                new Dog(20)
        };
        Dog[] bigDogs1 = largerThanFourNeighbors(dogs);

        for (int k = 0; k < bigDogs1.length; k += 1) {
            System.out.print(bigDogs1[k].weightInPounds + " ");
        }
        System.out.println();

    }
}
