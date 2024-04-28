
public class Queue {
	private int[] queue;
	private int top, last, elements, size;

	/** Constructor initializes queue with a standard size. (Here 20) */
	public Queue() {
		top = 0;
		last = 0;
		elements = 0;
		size = 20;
		setQueue(new int[size]);
	}

	/** Constructor initializes queue with the given size. */
	public Queue(int size) {
		top = 0;
		last = 0;
		elements = 0;
		this.size = size;
		setQueue(new int[size]);
	}

	/**
	 * Copy constructor initializes queue with another queue. This constructor must
	 * COPY all elements of the other queue. The elements of the other queue must
	 * NOT be changed!
	 */
	public Queue(Queue other) {
		this.setQueue(other.getQueue().clone());
		this.top = other.top;
		this.last = other.last;
		this.elements = other.elements;
		this.size = other.size;
	}

	/**
	 * Deinitializes the object; think about it and comment what to do here.
	 */
	protected void finalize() {
		setQueue(null); // Removes the pointer to the Object (Garbage collector does the rest)
	}

	/**
	 * Enqueues all elements from another queue onto this one. If another queue
	 * [4,5] is enqueued into this queue [1,2,3], the result is [1,2,3,4,5] and not
	 * [1,2,3,5,4]. The elements of the other queue must NOT be changed!
	 */
	public void enqueue(Queue other) {
		// Ensure enough space in this queue to accommodate elements from the other
		// queue
		if (elements + other.elements > size) {
			return;
		}
		// Enqueue elements from the other queue onto this queue
		for (int i = 0; i < other.elements; i++) {
			enqueue(other.getQueue()[(other.last + i) % other.size]);
		}
	}

	/**
	 * Clones this Queue instance and returns an exact COPY.
	 */
	public Queue clone() {
		return new Queue(this); // Using constructor to create clone
	}

	/**
	 * Returns true if the other queue is equal to this one, false otherwise. The
	 * contents of the two queues must not be changed!
	 */
	public boolean equals(Queue other) {
		// Check if the other queue is null or has a different size
		if (other == null || this.size != other.size) {
			return false;
		}

		// Compare elements of the two queues
		for (int i = 0; i < this.size; i++) {
			if (this.getQueue()[i] != other.getQueue()[i]) {
				return false; // If any elements are different, return false
			}
		}

		return true; // If all elements are the same, return true
	}

	/**
	 * Returns a string representation of the queue.
	 */
	public String toString() {
		StringBuilder queueString = new StringBuilder();
		queueString.append("[");
		for (int i = 0; i < elements; i++) {
			queueString.append(getQueue()[(last + i) % size]);
			if (i < elements - 1) {
				queueString.append(", ");
			}
		}
		queueString.append("]");
		return queueString.toString();
//		return this.queue.toString();
	}

	/**
	 * Returns true if the element val exists in the queue, false otherwise.
	 */
	public boolean search(int val) {
		for (int i = 0; i < this.size; i++) {
			if (getQueue()[(last + i) % size] == val) {
				return true;
			}
		}
		return false;
	}

	/** Clears all elements from the queue */
	public void clear() {
		top = 0;
		last = 0;
		elements = 0;
	}

	/** Enqueues an element at the back of the queue */
	public void enqueue(int val) {
		if (elements == getQueue().length) {
			return;
		}
		getQueue()[top] = val;
		top = (top + 1) % size;
		elements = elements + 1;
	}

	/** Dequeues the element at the front of the queue */
	public int dequeue() {
		if (elements == 0) {
			return -0; // Used -0 as error code
		}
		int number = getQueue()[last];
		last = (last + 1) % size;
		elements = elements - 1;
		return number;
	}

	/** Returns the front element of the queue without removing it */
	public int peek() {
		if (elements == 0) {
			return -0;
		}
		return getQueue()[last];
	}

	/** Returns the number of elements in the queue */
	public int elements() {
		return elements;
	}

	/** Returns the maximum size of the queue */
	public int size() {
		return getQueue().length;
	}

	public int[] getQueue() {
		return queue;
	}

	public void setQueue(int[] queue) {
		this.queue = queue;
	}
}
