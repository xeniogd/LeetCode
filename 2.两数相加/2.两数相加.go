package leetcode

//ListNode is:
//Definition for singly-linked list.
//type ListNode struct {
//    Val int
//    Next *ListNode
//}
type ListNode struct {
	Val  int
	Next *ListNode
}

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	carry := 0
	p := &ListNode{Val: 0}
	dummy := &ListNode{Val: 0}
	p = dummy

	for {
		if l1 == nil && l2 == nil && carry == 0 {
			break
		}

		if l1 != nil {
			carry += l1.Val
			l1 = l1.Next
		}

		if l2 != nil {
			carry += l2.Val
			l2 = l2.Next
		}

		p.Next = &ListNode{Val: carry % 10}
		carry /= 10
		p = p.Next
	}
	return dummy.Next
}
