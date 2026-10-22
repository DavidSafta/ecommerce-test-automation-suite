# E-commerce Test Automation Suite

![CI](https://github.com/DavidSafta/ecommerce-test-automation-suite/actions/workflows/ci.yml/badge.svg)

UI + API test automation suite for an e-commerce platform, built with Java, Cucumber (BDD), Selenide with the Page Factory pattern, REST Assured, Docker and GitHub Actions.

The UI tests run against [Automation Exercise](https://automationexercise.com), a practice e-commerce site. The API tests run against the public [GitHub REST API](https://docs.github.com/en/rest).

## Tech stack

- Java 25, Maven
- Cucumber 7.14.0 (BDD, Gherkin)
- Selenide 7.3.1 on top of Selenium WebDriver
- Page Object Model with Page Factory (`@FindBy`)
- REST Assured 5.4.0 (API testing)
- JUnit 4.13.2
- Allure Report (test reporting)
- Docker & Docker Compose (Selenium standalone Chrome)
- GitHub Actions (CI)

## What is covered

12 automated scenarios:

### Product search (`search.feature`)
- Data-driven (Scenario Outline): searching for "Dress", "Jeans" and "Top" displays search results

### Shopping cart (`cart.feature`)
- Adding the first product to the cart → the product appears in the cart

### Login (`login.feature`)
- Data-driven (Scenario Outline): 3 sets of invalid credentials → the error message is displayed

### GitHub API (`api.feature`)
- Data-driven (Scenario Outline): public profiles of 3 users → status 200 and matching `login`
- Non-existing user → status 404
- Public repository (`octocat/Hello-World`) → status 200, correct `name` and `owner.login`, `stargazers_count` present

## Design notes

- **Page Object Model with Page Factory**: page elements are declared with `@FindBy` and bound to the driver with `PageFactory.initElements`.
- **BasePage**: shared behavior (the cookie consent banner) lives in one class that all pages inherit.
- **Data-driven testing**: Scenario Outline + Examples run the same scenario with multiple data sets.
- **Remote browser support**: `Hooks` reads the `SELENIUM_REMOTE_URL` environment variable. If set, tests connect to a remote Chrome (Docker/CI); otherwise they use a local Chrome.
- **GitHub API rate limit**: unauthenticated calls are limited to 60 per hour per IP. `ApiSteps` uses the `GITHUB_TOKEN` environment variable when present, and the CI pipeline provides it.

## Project structure

    src/test/java
    ├── pages/    → Page Objects (BasePage, HomePage, CartPage, LoginPage)
    ├── steps/    → Cucumber step definitions
    ├── hooks/    → Cucumber hooks (browser setup and cleanup)
    └── runner/   → Cucumber TestRunner

    src/test/resources/features/  → Gherkin .feature files

## How to run

### Locally

Prerequisites: JDK 25, Maven, Google Chrome.

    mvn test

Or in IntelliJ: right-click `TestRunner.java` → **Run 'TestRunner'**.

Expected result: **12 tests passed**.

### With Docker

    docker compose up --build --exit-code-from tests
    docker compose down

This starts a Selenium Chrome container and a test container, and runs the full suite against it.

## Reports

Test results are written to `allure-results/`. With the [Allure CLI](https://allurereport.org) installed:

    allure serve allure-results

## CI/CD

Every push and pull request to `main` triggers the GitHub Actions workflow, which runs the full suite with Docker Compose and uploads `allure-results` as a build artifact. See the [Actions tab](https://github.com/DavidSafta/ecommerce-test-automation-suite/actions) for the build history.

## Roadmap

- [x] Project setup (Maven, Cucumber, Selenide)
- [x] Page Object Model with Page Factory
- [x] Data-driven scenarios (Scenario Outline)
- [x] API testing (REST Assured)
- [x] Allure reporting
- [x] Docker containerization
- [x] CI/CD with GitHub Actions
