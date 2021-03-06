package es.palmademallorca.bg.common.model;

public class Project
{
  private final String name;
  private WelcomePage welcomePage;

  public Project(String name)
  {
    this.name = name;
    
   // this.sampleTree = new SampleTree(new EmptySample(name));
  }

 /* public void addSample(String packagePath, Sample sample)
  {
    String packagesWithoutBase = "";
    try
    {
      if (!this.basePackage.equals(packagePath)) {
        packagesWithoutBase = packagePath.substring(this.basePackage.length() + 1);
      }
    }
    catch (StringIndexOutOfBoundsException e)
    {
      System.out.println("packagePath: " + packagePath + ", basePackage: " + this.basePackage);
      e.printStackTrace();
      return;
    }
    String[] packages = packagesWithoutBase.isEmpty() ? new String[0] : packagesWithoutBase.split("\\.");
    for (int i = 0; i < packages.length; i++)
    {
      String packageName = packages[i];
      if (!packageName.isEmpty())
      {
        packageName = packageName.substring(0, 1).toUpperCase() + packageName.substring(1);
        packageName = packageName.replace("_", " ");
        packages[i] = packageName;
      }
    }
    this.sampleTree.addSample(packages, sample);
  }

  public SampleTree getSampleTree()
  {
    return this.sampleTree;
  }
  */

  public void setWelcomePage(WelcomePage welcomePage)
  {
    if (null != welcomePage) {
      this.welcomePage = welcomePage;
    }
  }

  public WelcomePage getWelcomePage()
  {
    return this.welcomePage;
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("Project [ name: ");
    sb.append(this.name);
    sb.append(", sample count: ");
    sb.append(", tree: ");
    sb.append(" ]");

    return sb.toString();
  }


}
