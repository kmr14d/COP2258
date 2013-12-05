/* ========================================================================== */
/*      PROGRAM Automated Exam Grader

 AUTHOR: <Helen Parker>
 FSU MAIL NAME: <hdp12>
 RECITATION SECTION NUMBER: <009>
 RECITATION INSTRUCTOR NAME: <Danny>
 COP 3014 - Fall 2013
 PROJECT NUMBER: 5
 DUE DATE: Wednesday 11/13/2013
 PLATFORM: Windows OS / C++ 11 / Microsoft Visual C++ Express 2012 IDE

 SUMMARY

 This program analyzes test data.
 It analyzes the data file which contains the test answer key
 and each student's answers
 It determines each student's test score and the mean for the entire class
 It also generates a histogram using the test scores

 INPUT

 The input file is named xfile.txt


 OUTPUT

 Echoprinted input
 A table giving the list of student names in the order they were read in, the
 test answers for that student, and the student's test score. This table will
 incorporate data error messages if needed.
 The mean test score.
 A histogram showing the number of students who received scores in the
 following ranges: 0 thru 5, 6 thru 10, 11 thru 15, 16 thru 20, 21 thru 25,
 and 25 thru 30

 */

/* ========================================================================== */
/* HEADER FILES */

#include <iostream>
#include <string>
#include <fstream>

using namespace std;

typedef char answerList[30];
int studentGrade(answerList, answerList, int);
int grade;
int answerKey(answerList&, string);
int namesAndAnswers(answerList, ifstream&, string);

int main() {

	//char temp;
	//char scores;
	answerList testAnswers;
	answerList studentAnswers;

	ifstream inFile;

	// import the txt file of answers
	inFile.open("xfile.txt");

	// gets the line from the txt
	string line;
	getline(inFile, line);
	cout << "a" << line << endl;

	// establish the answer key
	answerKey(testAnswers, line);
	cout << endl << endl;

	// get the students names and answers
	namesAndAnswers(studentAnswers, inFile, line);

	cout << endl;

	cout << "the student got "
			<< studentGrade(testAnswers, studentAnswers, grade) << " right"
			<< endl << endl;

	grade = 0;
}

int answerKey(answerList& testAnswers, string line) {

	//establish the correct answers and save
	//them in the array testAnswers

	for (int i = 0; i < 30; i++) {
		testAnswers[i] = line[i];
		cout << testAnswers[i] << " ";
	}
	cout << "b";
	return 0;
}

int namesAndAnswers(answerList studentAnswers, ifstream& inFile, string line) {
	for (int i = 0; i < 25; i++) {

		// get the student's answers
		getline(inFile, line);
		cout << "c" << line << endl;

		getline(inFile, line);
		cout << "d" << line << endl;

		//student answers saved in studentAnswers array.
		for (int j = 0; j < 30; j++) {
			studentAnswers[j] = line[j];
			cout << studentAnswers[j] << " ";
		}
	}
	return 0;
}

int studentGrade(answerList testAnswers, answerList studentAnswers, int a) {
	for (int k = 0; k < 30; k++) {
		if (testAnswers[k] == studentAnswers[k]) {
			a++;
		}
	}
	return a;
}

