package com.splitwise.LLD.SplitwiseLLD;

import com.splitwise.LLD.SplitwiseLLD.model.Expense;
import com.splitwise.LLD.SplitwiseLLD.model.Group;
import com.splitwise.LLD.SplitwiseLLD.model.Transcation;
import com.splitwise.LLD.SplitwiseLLD.model.User;
import com.splitwise.LLD.SplitwiseLLD.service.ExpenseService;
import com.splitwise.LLD.SplitwiseLLD.service.GroupService;
import com.splitwise.LLD.SplitwiseLLD.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SplitwiseLldApplicationTests {



	@Test
	void testSplitwiseLldApplication() {

		UserService userService = new UserService();
		GroupService groupService = new GroupService();
		ExpenseService expenseService = new ExpenseService(groupService);

		//Register user:
		User u1= userService.registerUser("Salil", "abc", "1234578");
		User u2= userService.registerUser("Abhay", "def", "1284578");
		User u3=userService.registerUser("Deepak", "ghi", "1234578");
		User u4=userService.registerUser("Harry", "jkl", "1234578");

		//Create group
		List<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);

		Group group =groupService.createGroup("goa", users);

		Expense expense = expenseService.createExpense(50.00,u1,"cold-drinks",new Date(),group.getGroupId(),"equal", null);
		List<Transcation> transcations =  expenseService.settleExpense(group.getGroupId(), "default");

		transcations.forEach(transcation->{
			System.out.println(transcation.toString());
		});

		Expense expense1= expenseService.createExpense(200.00,u2,"petrol", new Date(),group.getGroupId(),"equal", null);
		List<Transcation> transcations1= expenseService.settleExpense(group.getGroupId(), "default");
		transcations1.forEach(transcation->{
			System.out.println(transcation.toString());
		});
	}

}
