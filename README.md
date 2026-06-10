# Compiler Design

A Java implementation of core compiler components including lexical analysis and symbol table management. This project demonstrates fundamental compiler design principles through a well-architected, production-quality codebase with comprehensive testing and code quality assurance.

## Project Overview

This project implements key stages of the compiler pipeline:

- **Lexical Analysis**: Tokenizes source code into meaningful tokens using a state machine pattern
- **Symbol Table**: Manages variable declarations, types, and scopes during compilation

The project emphasizes clean architecture, testability, and code quality with industry-standard practices including:
- Unit and integration testing
- Code coverage analysis (JaCoCo)
- Mutation testing (PIT)
- Security vulnerability scanning
- Code quality analysis

## Project Structure

```
compiler-design/
├── src/
│   ├── main/java/net/talaatharb/compiler/
│   │   ├── ProjectApplication.java          # Main entry point
│   │   ├── common/                           # Shared utilities
│   │   │   ├── SourceCodeFile.java          # Represents source code input
│   │   │   └── SourceCodeLocation.java      # Tracks line/column information
│   │   ├── lexical/                         # Lexical analysis phase
│   │   │   ├── LexicalAnalyzer.java         # Main analyzer (state machine)
│   │   │   ├── LexicalOutput.java           # Analysis results
│   │   │   ├── Token.java                   # Token interface
│   │   │   ├── tokens/                      # Specific token types
│   │   │   │   ├── AbstractToken.java       # Base token class
│   │   │   │   ├── KeywordToken.java        # Language keywords (int, if, return, etc.)
│   │   │   │   ├── IdentifierToken.java     # Variable/function names
│   │   │   │   ├── NumericToken.java        # Numbers (integer, float)
│   │   │   │   ├── StringToken.java         # String literals
│   │   │   │   ├── OperatorToken.java       # Operators (+, -, *, /, etc.)
│   │   │   │   ├── SpecialCharacterToken.java # Delimiters: (), {}, [], etc.
│   │   │   │   └── CommentToken.java        # Comments (ignored)
│   │   │   ├── enums/                       # Token classification
│   │   │   │   ├── KeywordEnum.java         # Language keywords
│   │   │   │   ├── OperatorEnum.java        # Operator definitions
│   │   │   │   ├── SpecialCharacterEnum.java # Delimiter definitions
│   │   │   │   ├── NumericTypesEnum.java    # Numeric token types
│   │   │   │   └── TokenEnum.java           # Token classification
│   │   │   ├── statemachine/                # Lexical state machine
│   │   │   │   ├── LexicalState.java        # State interface
│   │   │   │   ├── StartState.java          # Initial state
│   │   │   │   ├── NameBuildingState.java   # Identifier/keyword building
│   │   │   │   ├── NumericValueState.java   # Number parsing
│   │   │   │   ├── StringValueState.java    # String literal parsing
│   │   │   │   ├── EscapeStringValueState.java # Escape sequence handling
│   │   │   │   ├── OperatorState.java       # Operator recognition
│   │   │   │   ├── SpecialCharacterState.java # Delimiter recognition
│   │   │   │   ├── InlineCommentState.java  # Line comment handling (//)
│   │   │   │   ├── BlockCommentState.java   # Block comment handling (/* */)
│   │   │   │   └── ErrorState.java          # Error handling
│   │   │   └── errors/                      # Lexical error types
│   │   │       ├── LexicalException.java    # Base exception
│   │   │       └── MalformedTokenException.java # Invalid token error
│   │   └── symboltable/                     # Symbol table phase
│   │       ├── SymbolTable.java             # Main symbol table
│   │       ├── SymbolTableInterface.java    # Symbol table contract
│   │       ├── Symbol.java                  # Symbol representation
│   │       ├── SymbolTypeEnum.java          # Data types (int, float, etc.)
│   │       ├── SymbolSize.java              # Memory size information
│   │       ├── SymbolAddress.java           # Memory address tracking
│   │       └── SymbolDiminsion.java         # Array dimension tracking
│   └── test/java/net/talaatharb/compiler/
│       ├── ProjectApplicationTest.java      # Application integration tests
│       ├── lexical/
│       │   └── LexicalAnalyzerTest.java     # Comprehensive lexical analysis tests
│       └── symboltable/
│           └── SymbolTableTest.java         # Symbol table functionality tests
├── pom.xml                                   # Maven build configuration
└── .github/workflows/project-build.yml      # CI/CD pipeline
```

## How It Works

### Lexical Analysis

The `LexicalAnalyzer` uses a finite state machine to process source code character-by-character:

1. **Input**: Source code string
2. **Processing**: Each character transitions between states (e.g., StartState → NameBuildingState → StartState)
3. **Output**: Stream of tokens (keywords, identifiers, operators, literals)

**Example:**
```java
String code = "int x = 42;";
LexicalAnalyzer analyzer = new LexicalAnalyzer();
LexicalOutput output = analyzer.analyzeFile(new SourceCodeFile("test.java", code));

// Produces tokens:
// KeywordToken("int"), IdentifierToken("x"), OperatorToken("="), 
// NumericToken("42"), SpecialCharacterToken(";")
```

### Symbol Table

The `SymbolTable` maintains a mapping of identifiers to their properties:

- **Name**: Variable/function identifier
- **Type**: Data type (int, float, string, etc.)
- **Address**: Memory location
- **Size**: Memory size (in bytes)
- **Dimensions**: For arrays

**Example:**
```java
Symbol intVar = Symbol.builder()
    .name("x")
    .type(SymbolTypeEnum.INT)
    .address(new SymbolAddress(1024))
    .size(new SymbolSize(4))
    .build();

SymbolTable table = new SymbolTable();
table.insert(intVar);
Symbol found = table.lookup("x"); // Retrieves the symbol
```

## Building and Testing

### Prerequisites

- Java 25 (Temurin distribution)
- Maven 3.6+

### Build

```bash
cd compiler-design
mvn clean compile
```

### Run Tests

```bash
cd compiler-design
mvn test
```

### Full Verification (includes coverage checks)

```bash
cd compiler-design
mvn verify
```

This command runs:
- Unit tests and integration tests
- JaCoCo code coverage analysis (requires minimum 80% line coverage)
- PIT mutation testing
- Dependency security scanning
- Code quality analysis (PMD)

### Package

```bash
cd compiler-design
mvn package
```

Creates an executable JAR with all dependencies.

### Run Application

```bash
cd compiler-design
mvn package
java -jar target/compiler-design-0.0.1-SNAPSHOT.jar
```

Or directly:
```bash
cd compiler-design
mvn exec:java -Dexec.mainClass="net.talaatharb.compiler.ProjectApplication"
```

## Code Quality Standards

This project maintains high code quality standards:

| Tool | Purpose | Configuration |
|------|---------|---|
| **JaCoCo** | Code coverage | Minimum 80% line coverage required |
| **PIT** | Mutation testing | Detects survival of test mutations |
| **Dependency-Check** | Security scanning | Fails on high-severity vulnerabilities |
| **PMD** | Code quality | Detects code smells and anti-patterns |
| **Maven Surefire** | Unit testing | Runs all \*Test.java classes |
| **Maven Failsafe** | Integration testing | Runs \*IT.java classes |

## CI/CD Pipeline

The project includes a GitHub Actions workflow (`.github/workflows/project-build.yml`) that:

1. Checks out code on push to `master` branch
2. Sets up Java 25 environment
3. Builds and verifies the project
4. Runs all tests
5. Validates code coverage against minimum threshold
6. Fails the build if quality standards are not met

## Key Dependencies

- **Lombok**: Reduces boilerplate with annotations
- **MapStruct**: Type-safe bean mapping
- **SLF4J**: Logging abstraction
- **JUnit Jupiter**: Testing framework
- **Mockito**: Mocking framework
- **JaCoCo**: Code coverage
- **PIT**: Mutation testing
- **OWASP Dependency-Check**: Security scanning

## Development

### Code Style

- Follow standard Java conventions
- Use meaningful variable and method names
- Keep methods focused and small
- Add comments only for complex logic (self-documenting code preferred)
- Use Lombok annotations to reduce verbosity

### Testing Requirements

- All public methods must have tests
- Aim for 80%+ code coverage
- Use descriptive test names (e.g., `testAnalyzeFile_WithValidCode_ReturnsCorrectTokenCount`)
- Mock external dependencies
- Test both happy paths and error cases

## Example Usage

```java
// Create source code file
String sourceCode = """
    int calculateSum(int a, int b) {
        int result = a + b;
        return result;
    }
    """;

SourceCodeFile file = new SourceCodeFile("math.java", sourceCode);

// Analyze lexically
LexicalAnalyzer analyzer = new LexicalAnalyzer();
LexicalOutput output = analyzer.analyzeFile(file);

// Print results
List<Token> tokens = output.getTokens();
List<Exception> errors = output.getErrors();

System.out.println("Tokens: " + tokens.size());
System.out.println("Errors: " + errors.size());

// Manage symbols
SymbolTable symbolTable = new SymbolTable();
symbolTable.insert(Symbol.builder()
    .name("result")
    .type(SymbolTypeEnum.INT)
    .build());
```

## License

See LICENSE file for details.

## Author

Created by Talaat Harb

## Contributing

This is an educational project. Feel free to fork and extend with additional compiler phases like parsing, semantic analysis, or code generation.
