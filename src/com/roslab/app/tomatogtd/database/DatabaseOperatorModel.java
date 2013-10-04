package com.roslab.app.tomatogtd.database;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;

public interface DatabaseOperatorModel {
	
	/***
	 * 添加待办事项
	 * @param subject 事项标题
	 * @param remark 备注，可为null
	 * @param dueTime 截止日期，可谓null
	 * @return 操作是否成功
	 */
	public boolean addTodo(String subject, String remark, long dueTime);

	/***
	 * 添加待办事项
	 * @param subject 事项标题
	 * @param remark 备注，可为null
	 * @param dueTime 截止日期，可谓null
	 * @return 操作是否成功
	 */
	public boolean addUnPlanedTodo(String subject, String remark, long dueTime);
	
	/***
	 * 更改事项标题
	 * @param allTodosId 想要更改的事项的id
	 * @param subject 更改后的标题
	 * @return 操作是否成功
	 */
	public boolean updateSubject(int allTodosId, String subject);

	/***
	 * 更改事项的备注
	 * @param allTodosId 想要更改的事项的id
	 * @param remark 更改后的备注
	 * @return 操作是否成功
	 */
	public boolean updateRemark(int allTodosId, String remark);

	/***
	 * 更改事项的截止日期
	 * @param allTodosId 想要更改的事项的id
	 * @param dueTime 更改后的截止日期
	 * @return 操作是否成功
	 */
	public boolean updateDueTime(int allTodosId, long dueTime);
	
	/***
	 * 添加完成时间
	 * @param allTodosId 完成了的事项的id
	 * @param doneTime 完成的时间 
	 * @return 操作是否成功
	 */
	public boolean addDoneTime(int allTodosId, long doneTime);
	
	/***
	 * 删除事项
	 * @param allTodosID 要删除的事项的id
	 * @return 操作是否成功
	 */
	public boolean deleteTodos(int allTodosId);
	
	/***
	 * 查询今日待办 
	 * @return 今日待办列表，长度为0时表示为不存在待办
	 */
	public ArrayList<TodaysTodoItem> getTodaysTodoList();
	
	/***
	 * 添加内部中断
	 * @return 操作是否成功
	 */
	public boolean addInnerInterrupt(int todaysTodoId);
	
	/***
	 * 添加外部中断
	 * @return 操作是否成功
	 */
	public boolean addOutterInterrupt(int todaysTodoId);
	
	/***
	 * 添加今日待办
	 * @return 操作是否成功
	 */
	public boolean addTodaysTodo(int allTodoId, int firstEstimate);

	/***
	 * 为今日待办添加第二次番茄预估
	 * @param id 今日待办的id
	 * @param secondEstimate 第二次预估的番茄数
	 * @return 操作是否成功
	 */
	public boolean addTodaysTodoSecondEstimate(int todaysTodoId, int secondEstimate);
	
	/***
	 * 为今日待办添加第三次番茄预估
	 * @param id 今日待办的id
	 * @param thirdEstimate 第三次预估的番茄数
	 * @return 操作是否成功
	 */
	public boolean addTodaysTodoThirdEstimate(int todaysTodoId, int thirdEstimate);
	
	/***
	 * 为今日待办添加一个完成了的番茄数
	 * @param id 今日待办的id
	 * @return 操作是否成功
	 */
	public boolean addTodaysTodoFinishNumber(int todaysTodoId);
	
	/***
	 * 查询所有还未完成的待办事项
	 * @return 待办事项列表
	 */
	public ArrayList<AllTodosItem> getAllUndoneTodos();
	
	/***
	 * 查询所有已完成的待办事项列表
	 * @return 待办事项列表
	 */
	public ArrayList<AllTodosItem> getAllDoneTodos();
	
}
