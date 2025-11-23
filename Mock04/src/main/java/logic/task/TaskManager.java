package logic.task;


import interfaces.Duplicatable;

import java.util.ArrayList;

public class TaskManager {
	public static ArrayList<Task> getTaskByType(ArrayList<Class> types) {
    	// TODO implement this method
        ArrayList<Task> tasks = TaskList.getTasks();
        ArrayList<Task> returnTasks = new ArrayList<>();
        for (Task task : tasks){
            for (Class type : types)
                if (instanceOf(task.getClass(), type)) {
                    returnTasks.add(task);
                    break;
                }
        }
        return returnTasks;
    }
	
    public static void deleteDuplicateTasks() {
    	// TODO implement this method
        ArrayList<Task> tasks = TaskList.getTasks();
        for (int i = tasks.size() - 1; i >= 0; i--){
            Task task = tasks.get(i);
            if (task instanceof Duplicatable && task.getName().contains("-")){
                TaskList.removeTask(i);
            }
        }
    }

    public static boolean instanceOf(Class checkClass, Class interfaceClass) {
        return interfaceClass.isAssignableFrom(checkClass);
    }
}
