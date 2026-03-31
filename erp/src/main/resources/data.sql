-- Departments
INSERT INTO departments (name, description) VALUES
                                                ('Engineering', 'Software & Hardware Development'),
                                                ('Marketing',   'Brand & Digital Marketing'),
                                                ('HR',          'Human Resources & Talent Management');

-- Employees
INSERT INTO employees
(first_name, last_name, email, phone_number, position, salary, hire_date, department_id)
VALUES
    ('John',    'Doe',      'john.doe@company.com',     '08012345678', 'Software Engineer',  850000.00, '2024-01-15', 1),
    ('Jane',    'Smith',    'jane.smith@company.com',   '08098765432', 'Marketing Manager',  650000.00, '2023-11-01', 2),
    ('Mike',    'Johnson',  'mike.j@company.com',       '08123456789', 'HR Specialist',      450000.00, '2025-02-10', 3);