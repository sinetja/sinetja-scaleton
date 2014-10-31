Skeleton for creating your new Scala project based on
`Sinetja <https://github.com/sinetja/sinetja>`_.

See also:

* `Sinetja-Skeleton8 <https://github.com/sinetja/sinetja-skeleton8>`_:
  If you want to use Java 8 with its lambda syntax.
* `Sinetja-Skeleton <https://github.com/sinetja/sinetja-skeleton>`_:
  If you use older Java.

Run in development mode
~~~~~~~~~~~~~~~~~~~~~~~

::

  sbt/sbt run

Create project
~~~~~~~~~~~~~~

To create Eclipse project:

::

  sbt/sbt eclipse

To create IntelliJ project:

::

  sbt/sbt gen-idea

You then can run the project inside Eclipse or IntelliJ.

Template engine
~~~~~~~~~~~~~~~

For your convenience, this skeleton includes (see build.sbt) the
`superfast <https://github.com/lihaoyi/scalatags#performance>`_
`Scalatags <https://github.com/lihaoyi/scalatags>`_
template engine. You can use others if you want.

Scala XML
^^^^^^^^^

If you want to use Scala XML syntax, add to build.sbt:

::

  libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

Because XML is a little different than XHTML (e.g. closing tag), if you want to
render HTML, you need to do like this:

::

  import scala.xml.Xhtml

  res.respondHtml(Xhtml.toXhtml(
    <body>
      <p>Hi World</p>
    </body>
  ))

If you just return the XML as is, without the ``Xhtml.toXhtml`` converter,
the result may not be rendered correctly as you want.

Distribute
~~~~~~~~~~

To package for distribution:

::

  sbt/sbt xitrum-package

Directory ``target/xitrum`` will be created.

To run:

::

  cd target/xitrum
  script/start

It will run ``main`` method in class ``skeleton.App``.
Please modify the start script if you want to run other.
