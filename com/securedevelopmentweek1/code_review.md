***Code Review***

**1. Functionality and Correctness:**

- The code correctly implements DES encryption and decryption using Java OOP principles. It includes the functionality for encrypting and decrypting a message entered.

- Uses inbuilt library which is a good practice.

- Based on the lecture, DES is now condidered to be insecure in comparison to modern encryption standards such as RSA, SHA256 etc as the keys are much smaller.


**2. Readability and Style:**


- The code is well-organised with clear method names. There are many comments, maybe too many in places.


**3. Efficiency and Performance:**

- Implement different encryption algs based on the input size/ security required.


**4. Security Considerations:**

- DES



**5. Maintainability and Scalability:**

- It is currently very maintainable due to small scale.
- as the application grows, scalability will become an issue if the wrong algorithms are used.


**6. Testing:**

- There are no test cases provided currently.


**7. Dependencies**

- The dependencies on crypto and java.Base64 are standard and reasonable for encryption tasks such as this one.

- There are no proper external libraries, making the code lightweight but limited in functionality for encryption tasks.


**8. Code Duplication**

No significant duplication of code is seen as OOP practices are observed.

**9. Dependencies on External Services**

The code does not depend on any external services, which makes it portable and easy to run locally.