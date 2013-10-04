package com.roslab.app.tomatogtd.database;

import java.util.ArrayList;

import com.roslab.app.tomatogtd.enity.AllTodosItem;
import com.roslab.app.tomatogtd.enity.TodaysTodoItem;

public interface DatabaseOperatorModel {
	
	/***
	 * ��Ӵ�������
	 * @param subject �������
	 * @param remark ��ע����Ϊnull
	 * @param dueTime ��ֹ���ڣ���νnull
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodo(String subject, String remark, long dueTime);

	/***
	 * ��Ӵ�������
	 * @param subject �������
	 * @param remark ��ע����Ϊnull
	 * @param dueTime ��ֹ���ڣ���νnull
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addUnPlanedTodo(String subject, String remark, long dueTime);
	
	/***
	 * �����������
	 * @param allTodosId ��Ҫ���ĵ������id
	 * @param subject ���ĺ�ı���
	 * @return �����Ƿ�ɹ�
	 */
	public boolean updateSubject(int allTodosId, String subject);

	/***
	 * ��������ı�ע
	 * @param allTodosId ��Ҫ���ĵ������id
	 * @param remark ���ĺ�ı�ע
	 * @return �����Ƿ�ɹ�
	 */
	public boolean updateRemark(int allTodosId, String remark);

	/***
	 * ��������Ľ�ֹ����
	 * @param allTodosId ��Ҫ���ĵ������id
	 * @param dueTime ���ĺ�Ľ�ֹ����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean updateDueTime(int allTodosId, long dueTime);
	
	/***
	 * ������ʱ��
	 * @param allTodosId ����˵������id
	 * @param doneTime ��ɵ�ʱ�� 
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addDoneTime(int allTodosId, long doneTime);
	
	/***
	 * ɾ������
	 * @param allTodosID Ҫɾ���������id
	 * @return �����Ƿ�ɹ�
	 */
	public boolean deleteTodos(int allTodosId);
	
	/***
	 * ��ѯ���մ��� 
	 * @return ���մ����б�����Ϊ0ʱ��ʾΪ�����ڴ���
	 */
	public ArrayList<TodaysTodoItem> getTodaysTodoList();
	
	/***
	 * ����ڲ��ж�
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addInnerInterrupt(int todaysTodoId);
	
	/***
	 * ����ⲿ�ж�
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addOutterInterrupt(int todaysTodoId);
	
	/***
	 * ��ӽ��մ���
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodaysTodo(int allTodoId, int firstEstimate);

	/***
	 * Ϊ���մ�����ӵڶ��η���Ԥ��
	 * @param id ���մ����id
	 * @param secondEstimate �ڶ���Ԥ���ķ�����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodaysTodoSecondEstimate(int todaysTodoId, int secondEstimate);
	
	/***
	 * Ϊ���մ�����ӵ����η���Ԥ��
	 * @param id ���մ����id
	 * @param thirdEstimate ������Ԥ���ķ�����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodaysTodoThirdEstimate(int todaysTodoId, int thirdEstimate);
	
	/***
	 * Ϊ���մ������һ������˵ķ�����
	 * @param id ���մ����id
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addTodaysTodoFinishNumber(int todaysTodoId);
	
	/***
	 * ��ѯ���л�δ��ɵĴ�������
	 * @return ���������б�
	 */
	public ArrayList<AllTodosItem> getAllUndoneTodos();
	
	/***
	 * ��ѯ��������ɵĴ��������б�
	 * @return ���������б�
	 */
	public ArrayList<AllTodosItem> getAllDoneTodos();
	
}
