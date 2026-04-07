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