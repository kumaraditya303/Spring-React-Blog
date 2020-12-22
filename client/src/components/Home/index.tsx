import React, { Component } from "react";
import gallery1 from "../../assets/gallery-1.jpg";
import gallery2 from "../../assets/gallery-2.jpg";
import gallery3 from "../../assets/gallery-3.jpg";
import gallery4 from "../../assets/gallery-4.jpg";
class Home extends Component {
  render() {
    return (
      <>
        <section className="hero">
          <div className="container">
            <div className="row">
              <div className="col-lg-7">
                <h1>Fantastic Django Blog </h1>
                <br />
                <br />
                <br />
                <a href="#featured-posts" className="continue link-scroll">
                  Discover More
                </a>
              </div>
            </div>
            <a href="#intro" className="continue link-scroll">
              <i className="fa fa-long-arrow-down"></i> Scroll Down
            </a>
          </div>
        </section>
        <section className="intro" id="intro">
          <div className="container">
            <div className="row">
              <div className="col-lg-8">
                <h2 className="h3">
                  <em>Introduction</em>
                </h2>
                <div className="blockquote">
                  <blockquote>
                    The best portion of a man's life, his little, nameless, unremembered acts of kindness and love.{" "}
                  </blockquote>
                  <footer className="blockquote-footer">William Wordsworth</footer>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section className="featured-posts no-padding-top" id="featured-posts">
          <div className="container">
            <div className="row d-flex align-items-stretch">
              <div className="image col-lg-5">
                <img src=" post.thumbnail.url " alt="..." />
              </div>
              <div className="text col-lg-7">
                <div className="text-inner d-flex align-items-center">
                  <div className="content">
                    <header className="post-header">
                      <div className="category">
                        <a href="/">category</a>
                      </div>
                      <a href=" post.get_absolute_url ">
                        <h2 className="h4">post title</h2>
                      </a>
                    </header>
                    <p>post overview</p>
                    <footer className="post-footer d-flex align-items-center">
                      <a href="/" className="author d-flex align-items-center flex-wrap">
                        <div className="avatar">
                          <img src=" post.author.picture.url " alt="..." className="img-fluid" />
                        </div>
                        <div className="title">
                          <span> post author </span>
                        </div>
                      </a>
                      <div className="date">
                        <i className="icon-clock"></i>timesince
                      </div>
                      <div className="comments">
                        <i className="icon-comment"></i>comment count
                      </div>
                    </footer>
                  </div>
                </div>
              </div>
              <div className="image col-lg-5">
                <img src=" post.thumbnail.url " alt="..." />
              </div>
            </div>
          </div>
        </section>
        <section className="divider">
          <div className="container">
            <div className="row">
              <div className="col-md-7">
                <div className="blockquote">
                  <blockquote className="h2">
                    How does the Meadow flower its bloom unfold? Because the lovely little flower is free down to its
                    root, and in that freedom bold.
                  </blockquote>
                  <blockquote className="blockquote-footer">William Wordsworth</blockquote>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section className="latest-posts">
          <div className="container">
            <header>
              <h2>Latest from the blog</h2>
            </header>
            <div className="row">
              <div className="post col-md-4">
                <div className="post-thumbnail">
                  <a href=" post.get_absolute_url ">
                    <img src=" post.thumbnail.url " alt="" className="img-fluid" />
                  </a>
                </div>
                <div className="post-details">
                  <div className="post-meta d-flex justify-content-between">
                    <div className="date">post.timestamp </div>
                    <div className="category">
                      <a href="/"> category </a>
                    </div>
                  </div>
                  <a href=" post.get_absolute_url ">
                    <h3 className="h4"> post.title </h3>
                  </a>
                  <p className="text-muted"> post.overview|linebreaks|truncatewords:50 </p>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section className="newsletter no-padding-top">
          <div className="container">
            <div className="row">
              <div className="col-md-6">
                <h2>Subscribe to Newsletter</h2>
                <p className="text-big">
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                  et dolore magna aliqua.
                </p>
              </div>
              <div className="col-md-8">
                <div className="form-holder">
                  <form action="{% url 'index' %}" method="POST">
                    <div className="form-group">
                      <input type="email" name="email" id="email" placeholder="Type your email address" minLength={8} />
                      <input type="submit" className="submit" value="Subscribe" />
                    </div>
                  </form>
                  <div className="row">
                    <div className="col-md-8">
                      <div className="alert alert- message.tags  fade show" role="alert">
                        <p> message </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section className="gallery no-padding">
          <div className="row">
            <div className="mix col-lg-3 col-md-3 col-sm-6">
              <div className="item">
                <a href={gallery1} data-fancybox="gallery" className="image">
                  <img src={gallery1} alt="..." className="img-fluid" />
                  <div className="overlay d-flex align-items-center justify-content-center">
                    <i className="icon-search"></i>
                  </div>
                </a>
              </div>
            </div>
            <div className="mix col-lg-3 col-md-3 col-sm-6">
              <div className="item">
                <a href={gallery2} data-fancybox="gallery" className="image">
                  <img src={gallery2} alt="..." className="img-fluid" />
                  <div className="overlay d-flex align-items-center justify-content-center">
                    <i className="icon-search"></i>
                  </div>
                </a>
              </div>
            </div>
            <div className="mix col-lg-3 col-md-3 col-sm-6">
              <div className="item">
                <a href={gallery3} data-fancybox="gallery" className="image">
                  <img src={gallery3} alt="..." className="img-fluid" />
                  <div className="overlay d-flex align-items-center justify-content-center">
                    <i className="icon-search"></i>
                  </div>
                </a>
              </div>
            </div>
            <div className="mix col-lg-3 col-md-3 col-sm-6">
              <div className="item">
                <a href={gallery4} data-fancybox="gallery" className="image">
                  <img src={gallery4} alt="..." className="img-fluid" />
                  <div className="overlay d-flex align-items-center justify-content-center">
                    <i className="icon-search"></i>
                  </div>
                </a>
              </div>
            </div>
          </div>
        </section>
      </>
    );
  }
}
export default Home;
