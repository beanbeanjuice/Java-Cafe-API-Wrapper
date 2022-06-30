[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
[![WakaTime][wakatime-shield]][wakatime-url]
[![CodeFactor][codefactor-shield]][codefactor-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/beanbeanjuice/cafeBot">
    <img src="https://cdn.beanbeanjuice.com/images/cafeBot/readme/logo.gif" alt="Logo" width="260" height="186">
  </a>

  <h1 align="center">Cafe API Wrapper</h1>

  <p align="center">
    A wrapper for the Cafe API!
    <br />
    <a href="https://github.com/beanbeanjuice/cafe-api-wrapper"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/beanbeanjuice/cafe-api-wrapper">View Demo</a>
    ·
    <a href="https://github.com/beanbeanjuice/cafe-api-wrapper/issues">Report Bug</a>
    ·
    <a href="https://github.com/beanbeanjuice/cafe-api-wrapper/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
# About The Project

[![Product Name Screen Shot][product-title]](https://www.beanbeanjuice.com/cafeBot.html)

### Built With

* [Maven](https://maven.apache.org/)
* [Twitch4J](https://github.com/twitch4j/twitch4j)

<!-- GETTING STARTED -->
# Getting Started

To add this bot to your server, follow these steps.

## Prerequisites

In order to use this, you must have a Java project with either `Maven` or `Gradle`.

For `Maven`, paste these two repositories in your `pom.xml` file.
```XML
<repositories>
  ...

  <!-- Sonatype Snapshot Repository -->
  <repository>
    <id>sonatype-snapshots</id>
    <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
  </repository>

  <!-- Sonatype Release Repository -->
  <repository>
    <id>sonatype-releases</id>
      <url>https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/</url>
  </repository>

  ...
</repositories>
```

For `Gradle`, paste these two repositories in your `build.gradle` file.
```Groovy
maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
maven { url 'https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/' }
```

## Installation

For `Maven`, paste this into your `dependencies` section of your `pom.xml` file.
```XML
<!-- Cafe API -->
<dependency>
  <groupId>io.github.beanbeanjuice</groupId>
  <artifactId>cafe-api-wrapper</artifactId>
  <version>v1.1.1</version>
</dependency>
```

For `Gradle`, paste this into your `dependencies` section of your `build.gradle` file.
```Groovy
// Cafe API
implementation group: 'io.github.beanbeanjuice', name: 'cafe-api-wrapper', version: 'v1.1.1'
```

<!-- ROADMAP -->
# Roadmap

See the [open issues](https://github.com/beanbeanjuice/cafeBot/issues) for a list of proposed features (and known issues).

<!-- CONTRIBUTING -->
# Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
# License

Distributed under the GPL-3.0 License. See `LICENSE` for more information.

<!-- CONTACT -->
# Contact

- beanbeanjuice
- Twitter [@beanbeanjuice](https://twitter.com/beanbeanjuice)
- Email - beanbeanjuice@outlook.com
- Project Link: [GitHub](https://github.com/beanbeanjuice/cafeBot)

<!-- ACKNOWLEDGEMENTS -->
# Acknowledgements

* *There's nothing here yet... maybe in the future?*

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[contributors-url]: https://github.com/beanbeanjuice/cafe-api-wrapper/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[forks-url]: https://github.com/beanbeanjuice/cafe-api-wrapper/network/members
[stars-shield]: https://img.shields.io/github/stars/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[stars-url]: https://github.com/beanbeanjuice/cafe-api-wrapper/stargazers
[issues-shield]: https://img.shields.io/github/issues/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[issues-url]: https://github.com/beanbeanjuice/cafe-api-wrapper/issues
[license-shield]: https://img.shields.io/github/license/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[license-url]: https://github.com/beanbeanjuice/cafe-api-wrapper/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/beanbeanjuice
[wakatime-shield]: https://wakatime.com/badge/github/beanbeanjuice/cafe-api-wrapper.svg?style=for-the-badge
[wakatime-url]: https://wakatime.com/badge/github/beanbeanjuice/cafe-api-wrapper
[product-title]: http://cdn.beanbeanjuice.com/images/cafeBot/readme/cafeBot.png
[codefactor-shield]: https://www.codefactor.io/repository/github/beanbeanjuice/cafe-api-wrapper/badge?style=for-the-badge
[codefactor-url]: https://www.codefactor.io/repository/github/beanbeanjuice/cafe-api-wrapper