package org.fasttrackit.dev.todolist;



import java.util.*;

/**
 * Created by condor on April 04, 2015
 * FastTrackIT, 2015
 */
public class MyListOfToDoMock {

    private static MyListOfToDoMock m;

    public static MyListOfToDoMock getInstance() {

        if(m==null) {
            m=new MyListOfToDoMock();
            m.generateInitialList();
        }
       return m;
    }


    private List<ToDoBean> toDoList = new ArrayList<ToDoBean>();
    private int id;

    private void generateInitialList() {
        toDoList.add(new ToDoBean(1,"sa ud florile","21/10"));
        toDoList.add(new ToDoBean(2,"sa calc camasile", "21/11"));
        toDoList.add(new ToDoBean(3,"sa imi fac programare la dentist", "12/2"));
        toDoList.add(new ToDoBean(4,"sa verific presiunea la roata de la bicicleta", "3/11"));
        toDoList.add(new ToDoBean(5,"sa imi fac tema la geogra", "5/4"));
        toDoList.add(new ToDoBean(6,"sa imi sun bunica", "14/6"));
        id=6;
    };

   public void addItem(String value, String dd) {
      id++;
      toDoList.add(new ToDoBean(id,value, dd));
        }

    public void doneItem(int index) {
        for (ListIterator<ToDoBean> iter = toDoList.listIterator(); iter.hasNext(); ) {
            ToDoBean element = iter.next();

            if (element.getId()==index) {
                element.setDone(true);
                iter.set(element);
            }
        }
    }


    public void printList() {
        for (ListIterator<ToDoBean> iter = toDoList.listIterator(); iter.hasNext(); ) {
            ToDoBean element = iter.next();
            if (!element.isDone()) {
                System.out.print(element.getId() + ":");
                System.out.println(element.getWhatToDo());
                System.out.println(element.getDueDate());
            }

        }
    }

    public List getList() {
        return toDoList;
    }

}