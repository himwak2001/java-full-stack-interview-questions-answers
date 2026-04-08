### Core AWS IAM Concepts: Users & Groups

**What is IAM?**

IAM stands for **Identity and Access Management**. It is a **Global Service**, meaning you don't select a specific region (like us-east-1) to manage it. Your users, groups, and roles are available across all AWS regions globally.

<br>

**The Root Account**

- **Definition:** This is the identity created when you first set up your AWS account. It has complete administrative control over everything in the account.
- **Best Practice:** **Never use the Root account for daily tasks**. Even for developers, the best practice is to create an IAM User with specific permissions and use that instead.

<br>

**IAM Users**

- **Definition:** Represents a person (or sometimes an application) within your organization.
- **Key Property:** A user is a long-term credential. In an interview context, remember that **Users** are typically for humans, while Roles are for machines/applications.

<br>

**IAM Groups**

- **Definition:** A collection of IAM users.
- **Rules of Groups:**
  - **Flat Structure:** Groups cannot contain other groups (no nesting).
  - **Flexibility:** A user can belong to multiple groups (e.g., Charles is in both 'Developers' and 'Audit Team').
  - **Independence:** A user does not have to be in a group (e.g., Fred is a standalone user).


<br>

**Visualizing the IAM Hierarchy**

```mermaid
graph TD
    subgraph Groups
        D[Group: Developers]
        A[Group: Audit Team]
        O[Group: Operations]
    end

    U1(Alice) --> D
    U2(Bob) --> D
    U3(Charles) --> D
    U3 --> A
    U4(David) --> A
    U4 --> O
    U5(Edward) --> O
    U6(Fred) ---|Standalone| X[No Group]

    style U3 fill:#f9f,stroke:#333,stroke-width:2px
    style U4 fill:#f9f,stroke:#333,stroke-width:2px
```
- Note how Charles and David bridge two groups, inheriting permissions from both. This is the **Principle of Least Privilege** in action: you give users only the groups they need to perform their job.


<br>

### Core AWS IAM Concepts: Permissions & Policies

**What is an IAM Policy?**

- **JSON Documents:** Permissions are not checkboxes; they are defined using JSON (JavaScript Object Notation) documents.
- **Assignment:** These documents are attached to Users or Groups (and also Roles).
- **Purpose:** They define exactly which actions are allowed or denied on specific AWS resources.


<br>

**The Principle of Least Privilege**

This is a fundamental security concept in AWS.

- **Definition:** You should only grant the minimum permissions required for a user to perform their job.
- **Example:** If a developer only needs to read logs, don't give them "AdministratorAccess." Give them a policy that only allows `cloudwatch:GetLogEvents`.


<br>

**Deep Dive into the JSON Structure**

Based on the example in the slide, a policy consists of a `Version` and a `Statement` array. Each statement typically includes:

- Effect: Either `"Allow"` or `"Deny"`. (By default, everything is denied).
- Action: The specific API calls being permitted (e.g., `ec2:Describe*`). The `*` is a wildcard, meaning "all actions starting with Describe."
- Resource: The specific AWS resources the actions apply to. `"*"` means "all resources."


<br>

**Visualizing Policy Application**

```mermaid
graph LR
    A[IAM User/Group] -- Attached --> B{JSON Policy}
    B --> C[Statement 1: EC2 Describe]
    B --> D[Statement 2: ELB Describe]
    B --> E[Statement 3: CloudWatch Metrics]
    
    C --> F[AWS Cloud Resources]
    D --> F
    E --> F
```


<br>

**Hands-On: Creating Users and Groups**

1. **Service Discovery and User Creation:**
    ![My Image](screenshots/iam_image_1.png)
2. **Group Creation and Setting Permissions**
    ![My Image](screenshots/iam_image_2.png)
3. **Successful User Creation**
    ![My Image](screenshots/iam_image_3.png)
4. **Creating Custom Alias and Accessing IAM Users**
    ![My Image](screenshots/iam_image_4.png)


<br>

### IAM Policies

**IAM Policies Inheritance**

- **Group-Based Inheritance:** When a policy is attached to a group, every user in that group inherits those permissions automatically.
  - ***Example:*** Alice and Bob inherit the "Developers" policy.
- **Multi-Group Aggregation:** If a user belongs to multiple groups, they inherit the union of all permissions from those groups.
  - ***Example:*** Charles inherits permissions from both the "Developers" group and the "Audit Team" group.
- **Inline Policies:** These are policies attached directly to a specific user rather than a group.
  - ***Example:*** Fred has an "inline" policy. This is usually reserved for "one-off" permissions that shouldn't apply to anyone else.


<br>

**IAM Policy Structure (The JSON Deep-Dive)**

```json
{
  "Version": "2012-10-17",
  "Id": "S3-Account-Permissions",
  "Statement": [
    {
      "Sid": "1",
      "Effect": "Allow",
      "Principal": {
        "AWS": ["arn:aws:iam::123456789012:root"]
      },
      "Action": [
        "s3:GetObject",
        "s3:PutObject"
      ],
      "Resource": ["arn:aws:s3:::mybucket/*"],
      "Condition": { ... }
    }
  ]
}
```

| Element | Purpose | Requirement |
|:-|:-|:-|
| **Version** | Policy language version. Always use `"2012-10-17"`. | Required |
| **Id** | An optional identifier for the policy itself. | Optional |
| **Statement** | The main container for the policy rules. | Required |
| **Sid** | Statement ID; an optional label to distinguish statements. | Optional |
| **Effect** | Whether the rule Allows or Denies access. | Required |
| **Principal** | The specific account/user/role this policy applies to. | Required in some cases* |
| **Action** | The list of actions this policy allows or denies. | Required |
| **Resource** | The specific resource (ARN) the action applies to. | Required |
| **Condition** | Logic to determine when the policy is in effect (e.g., IP range). | Optional |


<br>

**Hands-On: Creating Policy**

1. **Policy Options:**
   ![My Image](screenshots/iam_image_5.png)
2. **Policy Creation:**
   ![My Image](screenshots/iam_image_6.png)
3. **Policy Validation:**
   ![My Image](screenshots/iam_image_7.png)


<br>

### IAM MFA

This covers the "human" side of IAM security - specifically, how to protect account access using Password Policies and Multi-Factor Authentication (MFA).

<br>

**IAM Password Policy**

A password policy is a set of rules defined by an administrator to ensure that all IAM users create strong, secure passwords.

#### Key Configuration Options

- **Complexity Requirements:** Force users to include uppercase, lowercase, numbers, and non-alphanumeric characters (e.g., `!@#$%`).
- **Minimum Length:** Set a character limit (e.g., at least 12 characters).
- **Password Rotation (Expiration):** Force users to change their password every 90 days.
- **Prevention of Re-use:** Prevents a user from switching back to an old password.
- **Self-Service:** You can allow users to change their own passwords to reduce administrative overhead.

<br>

**Multi-Factor Authentication (MFA)**

MFA is the single most important security layer for your AWS account. It follows the principle of:

**Something you know** (Password) + **Something you own** (Security Device) = **Successful Login**

#### The Main Benefit

If your password is stolen or hacked, the account remains safe because the attacker does not have physical access to your MFA device. **You should always enable MFA for your Root Account and high-privilege IAM users**.


<br>

**MFA Device Options in AWS**

AWS supports three main categories of MFA devices as shown in the table:

| Device Type | Examples | Description |
|:-|:-|:-|
| Virtual MFA | Google Authenticator, Authy | An app on your phone. Supports multiple tokens on one device. Great for developers. |
| U2F Security Key | YubiKey | A physical USB key. Supports multiple users/accounts on one single key. Very secure. |
| Hardware Key Fob | Gemalto, SurePassID | A dedicated physical device that displays a rotating code. Used in high-security/GovCloud environments. |


<br>

**Hands-On: IAM MFA**

1. **Setting Password Policy:**
   ![My Image](screenshots/iam_image_8.png)
2. **Setting MFA Policy:**
   ![My Image](screenshots/iam_image_9.png)
3. **Configuring MFA**
   ![My Image](screenshots/iam_image_10.png)