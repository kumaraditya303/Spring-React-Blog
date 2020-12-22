/* eslint-disable jsx-a11y/anchor-is-valid */
import React from "react";
function Layout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <header className="header">
        <nav className="navbar navbar-expand-lg">
          <div className="search-area">
            <div className="search-area-inner d-flex align-items-center justify-content-center">
              <div className="close-btn">
                <i className="icon-close"></i>
              </div>
              <div className="row justify-content-center">
                <div className="col-md-8">
                  <form action="/search">
                    <div className="form-group">
                      <input type="search" className="form-control" name="q" id="search" placeholder="What are you looking for?" />
                      <button type="submit" className="submit">
                        <i className="icon-search-1"></i>
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
          <div className="container">
            <div className="navbar-header d-flex align-items-center justify-content-between">
              <a href="/" className="navbar-brand">
                Fantastic Django Blog
              </a>
              <button
                type="button"
                data-toggle="collapse"
                data-target="#navbarcollapse"
                aria-controls="navbarcollapse"
                aria-expanded="false"
                aria-label="Toggle navigation"
                className="navbar-toggler"
              >
              </button>
            </div>
            <div id="navbarcollapse" className="collapse navbar-collapse">
              <ul className="d-flex navbar-nav">
                <li className="nav-item">
                  <a href="/" className="nav-link active ">
                    Home
                  </a>
                </li>
                <li className="nav-item">
                  <a href="/posts" className="nav-link ">
                    Blog
                  </a>
                </li>
                <li className="nav-item">
                  <a href="/post/create" className="nav-link ">
                    Create Post
                  </a>
                </li>
                <li className="nav-item">
                  <a href="{% url 'accounts_update' %}" className="nav-link ">
                    Profile
                  </a>
                </li>
                <li className="nav-item">
                  <a href="{% url 'logout' %}" className="nav-link ">
                    Logout
                  </a>
                </li>
                <li className="nav-item">
                  <a href="{% url 'login' %}" className="nav-link ">
                    Login / SignUp
                  </a>
                </li>
              </ul>
              <div className="navbar-text">
                <a href="/" className="search-btn">
                  <i className="icon-search-1"></i>
                </a>
              </div>
            </div>
          </div>
        </nav>
      </header>
      {children}
      <footer className="main-footer">
        <div className="container">
          <div className="row">
            <div className="col-md-4">
              <div className="logo">
                <h6 className="text-white">Fantastic Django Blog</h6>
              </div>
              <div className="contact-details">
                <p>53 Broadway, Broklyn, NY 11249</p>
                <p>Phone: (020) 123 456 789</p>
                <p>
                  Email: <a href="mailto:info@company.com">Info@Company.com</a>
                </p>
                <ul className="social-menu">
                  <li className="list-inline-item">
                    <a href="/">
                      <i className="fa fa-facebook"></i>
                    </a>
                  </li>
                  <li className="list-inline-item">
                    <a href="/">
                      <i className="fa fa-twitter"></i>
                    </a>
                  </li>
                  <li className="list-inline-item">
                    <a href="/">
                      <i className="fa fa-instagram"></i>
                    </a>
                  </li>
                  <li className="list-inline-item">
                    <a href="/">
                      <i className="fa fa-behance"></i>
                    </a>
                  </li>
                  <li className="list-inline-item">
                    <a href="/">
                      <i className="fa fa-pinterest"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            <div className="col-md-4">
              <div className="menus d-flex">
                <ul className="list-unstyled">
                  <li>
                    {" "}
                    <a href="/">My Account</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">Add Listing</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">Pricing</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">Privacy &amp; Policy</a>
                  </li>
                </ul>
                <ul className="list-unstyled">
                  <li>
                    {" "}
                    <a href="/">Our Partners</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">FAQ</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">How It Works</a>
                  </li>
                  <li>
                    {" "}
                    <a href="/">Contact</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div className="copyrights">
          <div className="container">
            <div className="row">
              <div className="col-md-6">
                <p>&copy; {new Date().getFullYear()} All rights reserved. Fantastic Django Blog</p>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </>
  );
}
export default Layout;
