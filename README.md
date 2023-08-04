#### Team-Boutique

# ȣ�� ���� ���� ����

`Back-End` �� `DB`�� ������ ȣ��, ����, ����Ʈ ���� �����ϴ� ����

## ?? ���� ȯ��
- ORACLE
 - SQLDeveloper
 - Eclipse
 - JAVA 8 ����

## ���� �Ұ�

<table>
    <tr>
        <td align="center">
	    <a href="https://github.com/ezurno">
	    	<img src="https://avatars.githubusercontent.com/u/108059303?v=4?s=100" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>���ظ�</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	</td>
        <td align="center">
	    <a href="https://github.com/sjsin0905">
	    	<img src="https://avatars.githubusercontent.com/u/97722177?v=4" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>������</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
        <td align="center">
	    <a href="https://github.com/gaamjaa">
	    	<img src="https://avatars.githubusercontent.com/u/49315208?v=4" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>���ؿ�</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
	<td align="center">
	    <a href="https://github.com/joareum">
	    	<img src="https://avatars.githubusercontent.com/u/43288938?v=4?s=100" width="100px;" alt=""/>
	    	<br/>
	    	<sub>
	    	<b>���Ƹ�</b>
	    	<br/>
	    	<img src="https://us-central1-progress-markdown.cloudfunctions.net/progress/100"/>
	        </sub>
	    </a>
	    <br />
	</td>
</table>

<br/>

## Usecase Diagram ����
- [Usecase Dirgram ���� ��](draw.io)
- �ش� ���踦 �����ϱ� ���� Usecase �� ����

<br/>
<img src="md_resources/resource_01.png" width=400/>
<br/>

���� ũ�� 3���� `����, ȸ������, ��ȸ` �� ���� �� �� ����

`����`�� �ؾ߸� ������ `����`, `���` �� �� �ֱ� ������ ����� ���� ���, ������ ***include ����***

<br/>

## DB �𵨸�

<br/>
<img src="md_resources/resource_02_DBModeling.png"  width=400/>
<br/>

`eXERD` �� �̿��� **DB �𵨸�**

`Customer` (��) �� `Accommodation` (����) �� **���� �� vs �� ����** �̹Ƿ� ���� `PK` �� `FK` �� �־� 1 vs �� ������ �������־�� ��

���� `Book` (����) table �� ������ ���üȭ ��

<br/>

## Class Diagram ����

<br/>
<img src="md_resources/resource_05_CD.drawio.png" width=600/>
<br/>

����� **DB �𵨸��� �̿��� Class Diagram �� ����** ����

������ `Hotel`, `Motel`, `Resort`, `Pension` �� Ư���� ������ ���� ������ `Accommodation` �� ��ӹ���

<br/>

## FE-UI ����
- FE ����� ������� �ʾƵ� ���ȭ���� ������ �ؾ� ������ �����ϱ� ����
- ����ġ ���� �κ��� ���ø� �� ����
- ���� �����ڷ� �ΰ��� ����

<br/>
<img src="md_resources/resource_04_Customer.png" width=600/>
<br/>

��� �ٿ� ���� ���� ������ ã�� �� ����

`ȣ��`, `����`, `����Ʈ`, `�Ҽ�` ��ư�� �̿��� �ϴ��� `LIST` �� �ش� ���ǿ� �´� ��� �� �� ����

`���ݴ뺰`, `������`�� ��ȸ ����

<br/>

Ư�� ȣ���� Ŭ�� �� ���೯¥�� ����, ���� ��� �� ���� �� ����

<br/>

**�������� ȸ������ ���Զ��� ������ �ش� ������ ����ϸ� �ֹε�Ϲ�ȣ (SSN) �� �ߺ����� �ʴ� �̻� �ش� ���� ���**

�ߺ��Ǵ� SSN �� ���� ���� ��� `Exception` �߻�

<br/>

�ϴܿ� �ش� ���� ���� ������ ��ȸ ����, ���� ���� ��¥�� ������ ��� �ߴܿ� ��¥ ������ �� �� ����

<br/>
<img src="md_resources/resource_03_Admin.png" width=600/>
<br/>

�ش� ȭ���� ������ ��.

����� �˻� �ٿ��� **���̵�**, **�̸�**, **����** �� ī�װ��� ��ġ�ϴ� ���� ã�� ����Ʈ�� ���

���� ȣ���� �߰��� ��� �ϴ��� ��ü��� **Radio-Button** �� �̿��� ���� �ü��� ����, ȣ���� ����� ������� ���� ���� �� �ִ� ���밡���ο� �Է�

�Է� ���� ������ ��ϰ� ������ ������

<br/>

���� ����ȸ ���� �� ��ȸ�� �� �� ������ **���̵�**, **�̸�**�� �̿��� ��ȸ

�ϴܿ� ***���� �������� ��ȸ*** �� �� ����

�� ***�������� ���� ���� ���� ����Ʈ***�� �� �� ����