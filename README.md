# Material Breadcrumbs Navigation

[![Build status][travis-image]][travis-url] [![Dependency Status][dependency-image]][dependency-url]

Check if server is online via socket.io

##Installation##
Just clone git repository

``git clone https://github.com/MDXDave/socket.io-status.git``

and install dependencies with

``npm install``

##Usage##
**Server side:** 

Open ``config.json`` and change *redirect_uri* to your domain (this redirect is used when someone access the node.js server directly), you can also change *port* to a port you would like to use.

To enable ssl just change ssl *enabled* to *true* and specify the path to your keyfile and certfile.

Start server with 

``node status.js``

**Client side:**

See ``examples/status.html`` for usage

##Changelog##
**Version 0.2**

- added support for SSL

**Version 0.1**

- initial release

[travis-image]: https://img.shields.io/travis/MDXDave/materialbreadcrumbsnavigation/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/MDXDave/materialbreadcrumbsnavigation
[dependency-image]: http://img.shields.io/david/MDXDave/materialbreadcrumbsnavigation.svg?style=flat-square
[dependency-url]: https://david-dm.org/MDXDave/materialbreadcrumbsnavigation

 